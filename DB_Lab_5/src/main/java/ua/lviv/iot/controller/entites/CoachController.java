package ua.lviv.iot.controller.entites;

import ua.lviv.iot.controller.GeneralController;
import ua.lviv.iot.dal.dao.entities.CoachDao;
import ua.lviv.iot.models.entity.Coach;

public class CoachController extends GeneralController<Coach, Integer> {
    public CoachController() {
        super(new CoachDao());
    }
    @Override
    public Coach createNewEntity() {
        Coach newCoach = new Coach();
        System.out.println("Enter Coach name:");
        newCoach.setName(input.nextLine());
        System.out.println("Enter Coach surname:");
        newCoach.setSurname(input.nextLine());
        System.out.println("Enter Coach phone number:");
        newCoach.setPhoneNumber(input.nextLine());
        System.out.println("Enter Coach price:");
        newCoach.setPrice(Double.parseDouble(input.nextLine()));
        return newCoach;
    }

    @Override
    public Coach createUpdEntity(String stringId) {
        Integer id = Integer.parseInt(stringId);
        Coach updatedCoach =  createNewEntity();
        if (updatedCoach != null) {
            updatedCoach.setId(id);
            return updatedCoach;
        }
        return null;
    }
}
