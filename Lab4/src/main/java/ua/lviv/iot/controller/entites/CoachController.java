package ua.lviv.iot.controller.entites;

import ua.lviv.iot.controller.GeneralController;
import ua.lviv.iot.dal.dao.entities.CoachDao;
import ua.lviv.iot.models.entity.Coach;

public class CoachController extends GeneralController<Coach, Integer> {
    public CoachController() {
        super(new CoachDao());
    }
}
