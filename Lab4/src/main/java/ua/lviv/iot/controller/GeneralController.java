package ua.lviv.iot.controller;

import ua.lviv.iot.dal.dao.GeneralDao;
import ua.lviv.iot.models.manager.Manager;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


public class GeneralController<Entity, Id> implements AbstractController<Entity, Id> {
    private final GeneralDao<Entity, Id> dao;
    private final Manager<Entity, Id> manager;

    public GeneralController(final GeneralDao<Entity, Id> dao) {
        this.dao = dao;
        this.manager = dao.getManager();
    }

    @Override
    public List<Entity> getAll() {
        return dao.getAll();
    }

    @Override
    public Entity getById(final String idString) {
        return dao.getById((Id) idString);
    }

    @Override
    public boolean create() {
        try {
            Entity createdItem = manager.createEntity();
            return dao.create(createdItem);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            System.out.println("An error occurred during entity creation");
            return false;
        }
    }

    @Override
    public boolean update(final String idString) {
        try {
            Entity updItem = manager.createEntity();
            return dao.update((Id) idString, updItem);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            System.out.println("An error occurred during setting field(s) value");
            return false;
        }
    }

    @Override
    public boolean delete(final String idString) {
        return dao.delete((Id) idString);
    }
}
