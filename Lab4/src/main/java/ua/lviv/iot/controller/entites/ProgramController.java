package ua.lviv.iot.controller.entites;

import ua.lviv.iot.controller.GeneralController;
import ua.lviv.iot.dal.dao.entities.ProgramDao;
import ua.lviv.iot.models.entity.Program;

public class ProgramController extends GeneralController<Program, Integer> {
    public ProgramController() {
        super(new ProgramDao());
    }
}
