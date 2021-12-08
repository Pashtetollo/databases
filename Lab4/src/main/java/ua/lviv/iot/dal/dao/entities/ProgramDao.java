package ua.lviv.iot.dal.dao.entities;

import ua.lviv.iot.dal.dao.GeneralDao;
import ua.lviv.iot.models.entity.Program;

public class ProgramDao extends GeneralDao<Program, Integer> {
    public ProgramDao() {
        super(Program.class);
    }
}
