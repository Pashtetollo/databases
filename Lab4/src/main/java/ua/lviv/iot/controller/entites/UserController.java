package ua.lviv.iot.controller.entites;

import ua.lviv.iot.controller.GeneralController;
import ua.lviv.iot.dal.dao.entities.UserDao;
import ua.lviv.iot.models.entity.User;

public class UserController extends GeneralController<User, Integer> {
    public UserController() {
        super(new UserDao());
    }
}
