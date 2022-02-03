package ua.lviv.iot.dal.dao.entities;

import ua.lviv.iot.dal.dao.GeneralDao;
import ua.lviv.iot.models.entity.Exersice;

public class ExersiceDao extends GeneralDao<Exersice, Integer> {
    public ExersiceDao() {
        super(Exersice.class);
    }
}
