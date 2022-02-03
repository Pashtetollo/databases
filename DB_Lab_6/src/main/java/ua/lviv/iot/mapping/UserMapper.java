package ua.lviv.iot.mapping;

import org.modelmapper.AbstractConverter;
import ua.lviv.iot.models.DTO.UserDto;
import ua.lviv.iot.models.entity.User;


public class UserMapper extends AbstractConverter<User, UserDto> {
    @Override
    protected UserDto convert(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .coach(user.getCoach())
                .build();
    }
}