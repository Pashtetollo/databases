package ua.lviv.iot.models.transformer;

import lombok.Getter;
import ua.lviv.iot.models.manager.Manager;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transformer<Entity, Id> {
    @Getter
    private final Manager<Entity, Id> manager;

    public Transformer(Manager<Entity, Id> entityIdManager) {
        manager = entityIdManager;
    }

    public Entity castResultSetToEntity(ResultSet resultSet) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
            SecurityException, SQLException {
        Entity entity = manager.getManagedClass().getConstructor().newInstance();
        for (Field field: manager.getFields()) {
            String name = field.getAnnotation(Column.class).name();
            field.setAccessible(true);
            Class<?> fieldType = field.getType();
            field.set(entity, resultSet.getObject(name, fieldType));
        }
        return entity;
    }
}
