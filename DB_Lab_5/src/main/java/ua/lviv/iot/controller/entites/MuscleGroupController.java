package ua.lviv.iot.controller.entites;

import ua.lviv.iot.controller.GeneralController;
import ua.lviv.iot.dal.dao.entities.MuscleGroupDao;
import ua.lviv.iot.models.entity.MuscleGroup;

public class MuscleGroupController extends GeneralController<MuscleGroup, Integer> {
    public MuscleGroupController() {
        super(new MuscleGroupDao());
    }
}
