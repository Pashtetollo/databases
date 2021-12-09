package ua.lviv.iot.controller;

import java.util.List;

public interface AbstractController<Entity, Id> {
    List<Entity> getAll();
    Entity getById(String idString);
    boolean create();
    boolean update(String idString);
    boolean delete(String idString);
}
