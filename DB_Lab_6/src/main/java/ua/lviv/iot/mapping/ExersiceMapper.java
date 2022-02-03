package ua.lviv.iot.mapping;

import org.modelmapper.AbstractConverter;
import ua.lviv.iot.models.DTO.ExersiceDto;
import ua.lviv.iot.models.entity.Exersice;


public class ExersiceMapper extends AbstractConverter<Exersice, ExersiceDto > {
    @Override
    protected ExersiceDto convert(Exersice exersice) {
        return ExersiceDto.builder()
                .id(exersice.getId())
                .name(exersice.getName())
                .targeted_bodypart(exersice.getTargeted_bodypart())
                .build();
    }
}