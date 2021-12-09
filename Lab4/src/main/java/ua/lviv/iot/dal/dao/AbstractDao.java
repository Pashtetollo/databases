package ua.lviv.iot.dal.dao;

import java.util.List;

public interface AbstractDao<Entity, Id> {
    List<Entity> getAll();
    Entity getById(Id id);
    boolean create(Entity newItem);
    boolean update(Id id, Entity updItem);
    boolean delete(Id id);
}
