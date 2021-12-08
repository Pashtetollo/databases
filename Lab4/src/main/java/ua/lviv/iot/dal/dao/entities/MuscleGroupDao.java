package ua.lviv.iot.dal.dao.entities;

import ua.lviv.iot.dal.dao.GeneralDao;
import ua.lviv.iot.models.entity.MuscleGroup;

public class MuscleGroupDao extends GeneralDao<MuscleGroup, Integer> {
    public MuscleGroupDao() {
        super(MuscleGroup.class);
    }
}
