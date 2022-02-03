package ua.lviv.iot.mapping;

import org.modelmapper.AbstractConverter;
import ua.lviv.iot.models.DTO.MuscleGroupDto;
import ua.lviv.iot.models.entity.MuscleGroup;

public class MuscleGroupMapper extends AbstractConverter<MuscleGroup, MuscleGroupDto> {
    @Override
    protected MuscleGroupDto convert(MuscleGroup muscleGroup) {
        return MuscleGroupDto.builder()
                .id(muscleGroup.getId())
                .name(muscleGroup.getName())
                .build();
    }
}