package ua.lviv.iot.dal.dao.entities;

import ua.lviv.iot.dal.dao.GeneralDao;
import ua.lviv.iot.models.entity.Coach;

public class CoachDao extends GeneralDao<Coach, Integer> {
    public CoachDao() {
        super(Coach.class);
    }
}
