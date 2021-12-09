package ua.lviv.iot.dal.dao.entities;

import ua.lviv.iot.dal.dao.GeneralDao;
import ua.lviv.iot.models.entity.User;

public class UserDao extends GeneralDao<User, Integer> {
    public UserDao() {
        super(User.class);
    }
}
