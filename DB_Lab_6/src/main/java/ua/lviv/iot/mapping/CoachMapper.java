package ua.lviv.iot.mapping;

import org.modelmapper.AbstractConverter;
import ua.lviv.iot.models.DTO.CoachDto;
import ua.lviv.iot.models.entity.Coach;

public class CoachMapper extends AbstractConverter<Coach, CoachDto> {
    @Override
    protected CoachDto convert(Coach coach) {
        return CoachDto.builder()
                .id(coach.getId())
                .name(coach.getName())
                .surname(coach.getSurname())
                .phoneNumber(coach.getPhoneNumber())
                .price(coach.getPrice())
                .build();
    }
}
