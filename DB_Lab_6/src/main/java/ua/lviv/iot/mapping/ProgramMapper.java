package ua.lviv.iot.mapping;

import org.modelmapper.AbstractConverter;
import ua.lviv.iot.models.DTO.ProgramDto;
import ua.lviv.iot.models.entity.Program;


public class ProgramMapper extends AbstractConverter<Program, ProgramDto> {
    @Override
    protected ProgramDto convert(Program program) {
        return ProgramDto.builder()
                .id(program.getId())
                .name(program.getName())
                .build();
    }
}