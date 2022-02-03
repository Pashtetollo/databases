package ua.lviv.iot.controller.entites;

import ua.lviv.iot.controller.GeneralController;
import ua.lviv.iot.dal.dao.entities.ExersiceDao;
import ua.lviv.iot.models.entity.Exersice;

public class ExersiceController extends GeneralController<Exersice, Integer> {
    public ExersiceController() {
        super(new ExersiceDao());
    }
}
