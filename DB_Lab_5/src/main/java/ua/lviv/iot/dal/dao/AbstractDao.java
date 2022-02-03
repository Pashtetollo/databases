package ua.lviv.iot.dal.dao;

import java.io.Serializable;
import java.util.List;

public interface AbstractDao<Entity, Id extends Serializable> {
    List<Entity> getAll();
    Entity getById(Id id);
    boolean create(Entity newItem);
    boolean update(Entity updItem);
    boolean delete(Id id);
}
