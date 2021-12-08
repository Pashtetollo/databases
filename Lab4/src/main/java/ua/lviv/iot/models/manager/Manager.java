package ua.lviv.iot.models.manager;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Getter
public class Manager<Entity, Id> {
    private final Class<Entity> managedClass;
    private final List<Field> fields;
    private final List<Field> inputableFields;
    private Field primaryKey;
    private final Scanner input;

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

    public String getTableName() {
        return managedClass.getAnnotation(Table.class).name();
    }

    public String getPkName() {
        if (primaryKey != null) {
            return primaryKey.getAnnotation(Column.class).name();
        }
        return null;
    }

    public Id getEntityPk(Entity entity) throws IllegalAccessException {
        return (Id) primaryKey.get(entity);
    }

    public List<String> getColumnsNames() {
        List<String> columnsNames = new ArrayList<>();
        for (Field column : fields) {
            columnsNames.add(column.getAnnotation(Column.class).name());
        }
        return columnsNames;
    }


    public List<String> getInputableColumnsNames() {
        List<String> inputableColumnsNames = new ArrayList<>();
        for (Field column : inputableFields) {
            inputableColumnsNames.add(column.getAnnotation(Column.class).name());
        }
        return inputableColumnsNames;
    }

    public void setColumnValueByName(Entity entity, Field fieldToInput, String fieldValue)
            throws IllegalArgumentException, IllegalAccessException {
        fieldToInput.setAccessible(true);
        if (fieldValue == null) {
            fieldToInput.set(entity, null);
        } else if (fieldToInput.getType() == Integer.class) {
            fieldToInput.set(entity, Integer.parseInt(fieldValue));
        } else if (fieldToInput.getType() == LocalDate.class) {
            fieldToInput.set(entity, LocalDate.parse(fieldValue));
        } else if (fieldToInput.getType() == Boolean.class) {
            fieldToInput.set(entity, Boolean.getBoolean(fieldValue));
        } else {
            fieldToInput.set(entity, fieldValue);
        }
    }

    public Entity createEntity()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Entity entity = managedClass.getConstructor().newInstance();

        for (Field field: inputableFields) {
            System.out.printf("Please, enter %s %s value:%n", entity.getClass().getSimpleName(), field.getName());
            String fieldValue =  input.nextLine();
            if (fieldValue.trim().equals("")) {
                setColumnValueByName(entity, field, null);
            } else {
                setColumnValueByName(entity, field, fieldValue);
            }
        }

        return entity;
    }

    public String getCreateColumnsString(final Entity entity) throws IllegalAccessException {
        StringBuilder createColumnsString = new StringBuilder();
        for (Field column : this.inputableFields) {
            column.setAccessible(true);
            if (column.get(entity) == null) {
                createColumnsString.append("NULL");
            } else if (column.getType() == LocalDate.class || column.getType() == String.class) {
                createColumnsString.append("'");
                createColumnsString.append(column.get(entity).toString());
                createColumnsString.append("'");
            } else {
                createColumnsString.append(column.get(entity).toString());
            }
            createColumnsString.append(", ");
        }
        createColumnsString.delete(createColumnsString.length() - 2, createColumnsString.length() - 1);
        return createColumnsString.toString();
    }

    public String getUpdateColumnsString(Entity entity) throws IllegalAccessException {
        StringBuilder updateColumnsString = new StringBuilder();
        List<Field> columns = this.getInputableFields();
        List<String> columnsNames = this.getInputableColumnsNames();
        for (int i = 0; i < columns.size(); i++) {
            Field column = columns.get(i);
            String columnName = columnsNames.get(i);
            column.setAccessible(true);
            updateColumnsString.append(columnName);
            updateColumnsString.append("=");
            if (column.get(entity) == null) {
                updateColumnsString.append("NULL");
            } else if (column.getType() == LocalDate.class || column.getType() == String.class) {
                updateColumnsString.append("'");
                updateColumnsString.append(column.get(entity).toString());
                updateColumnsString.append("'");
            } else {
                updateColumnsString.append(column.get(entity).toString());
            }
            updateColumnsString.append(", ");
        }
        updateColumnsString.delete(updateColumnsString.length() - 2, updateColumnsString.length() - 1);
        return updateColumnsString.toString();
    }
}
