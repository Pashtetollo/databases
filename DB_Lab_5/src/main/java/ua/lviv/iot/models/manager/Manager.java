package ua.lviv.iot.models.manager;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Getter
public class Manager<Entity, Id extends Serializable> {
    private final Class<Entity> managedClass;
    private final List<Field> fields;
    private final List<Field> inputableFields;
    private final Scanner input;
    private Field primaryKey;

    public Manager(Class<Entity> managedClass){
        this.input = new Scanner(System.in);
        this.managedClass = managedClass;
        this.inputableFields = new ArrayList<>();
        this.fields = new ArrayList<>();
        for (Field field: managedClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(javax.persistence.Id.class)) {
                primaryKey = field;
            }
            if (!field.isAnnotationPresent(GeneratedValue.class)) {
                inputableFields.add(field);
            }
            if (field.isAnnotationPresent(Column.class)) {
                this.fields.add(field);
            }
        }
    }

    public Id getEntityPk(Entity entity) throws IllegalAccessException {
        return (Id) primaryKey.get(entity);
    }

    public Id stringIdToPk(String stringId){
        if (primaryKey.getType() == Integer.class) {
            Id id = (Id) (Serializable) Integer.parseInt(stringId);
            return id;
        }
        else {
            return (Id) stringId;
        }
    }

    public void setColumnValueByName(Entity entity, Field fieldToInput, String fieldValue)
            throws IllegalArgumentException, IllegalAccessException {
        fieldToInput.setAccessible(true);
        if (fieldValue == null) {
            fieldToInput.set(entity, null);
        } else if (fieldToInput.getType() == Integer.class) {
            fieldToInput.set(entity, Integer.parseInt(fieldValue));
        } else if (fieldToInput.getType() == Long.class) {
            fieldToInput.set(entity, Long.parseLong(fieldValue));
        } else if (fieldToInput.getType() == Double.class) {
            fieldToInput.set(entity, Double.parseDouble(fieldValue));
        } else {
            fieldToInput.set(entity, fieldValue);
        }
    }

    public Entity createNewEntity(List<Field> fieldsToInsert)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Entity entity = managedClass.getConstructor().newInstance();

        for (Field field: fieldsToInsert) {
            System.out.printf("Please, enter %s %s value:\n", entity.getClass().getSimpleName(), field.getName());
            String fieldValue =  input.nextLine();
            if (fieldValue.trim().equals("")) {
                setColumnValueByName(entity, field, null);
            } else {
                setColumnValueByName(entity, field, fieldValue);
            }
        }

        return entity;
    }

    public Entity createEntity()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return createNewEntity(inputableFields);
    }

    public Entity updateEntity()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException{
        return createNewEntity(fields);
    }

}