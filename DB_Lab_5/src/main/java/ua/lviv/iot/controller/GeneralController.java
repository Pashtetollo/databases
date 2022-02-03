package ua.lviv.iot.controller;

import lombok.Getter;
import ua.lviv.iot.dal.dao.GeneralDao;
import ua.lviv.iot.models.manager.Manager;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Scanner;


public abstract class GeneralController<Entity, Id extends Serializable> implements AbstractController<Entity> {
    private final GeneralDao<Entity, Id> dao;
    protected final Scanner input = new Scanner(System.in);
    @Getter
    private final Manager<Entity, Integer> manager;

    public GeneralController(final GeneralDao<Entity, Id> dao) {
        this.dao = dao;
        this.manager = new Manager<>(dao.getEntityClass());
    }

    @Override
    public List<Entity> getAll() {
        return dao.getAll();
    }

    @Override
    public Entity getById(final String idString) {
        return dao.getById((Id) manager.stringIdToPk(idString));
    }

    @Override
    public boolean create() {
        try {
            Entity createdItem =createNewEntity();
            return dao.create(createdItem);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            System.out.println("An error occurred during entity creation");
            return false;
        }
    }

    @Override
    public boolean update(final String idString) {
        try {
            Entity updItem = createUpdEntity(idString);
            return dao.update(updItem);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            System.out.println("An error occurred during setting field(s) value");
            return false;
        }
    }

    @Override
    public boolean delete(final String idString) {
        return dao.delete((Id) manager.stringIdToPk(idString));
    }

    @Override
    public Entity createNewEntity()
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return manager.createEntity();
    }

    @Override
    public Entity createUpdEntity(String stringId)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return manager.updateEntity();
    }
}
