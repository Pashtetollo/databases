package ua.lviv.iot.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface AbstractController<Entity> {
    List<Entity> getAll();
    Entity getById(String idString);
    boolean create();
    boolean update(String idString);
    boolean delete(String idString);
    Entity createNewEntity () throws InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException;
    Entity createUpdEntity(String stringId) throws InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException;
}
