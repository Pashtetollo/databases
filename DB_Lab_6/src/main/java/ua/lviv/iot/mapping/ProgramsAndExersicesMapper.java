package ua.lviv.iot.mapping;

import org.modelmapper.AbstractConverter;
import ua.lviv.iot.models.DTO.ProgramsAndExersicesDto;
import ua.lviv.iot.models.entity.ProgramsAndExercises;

public class ProgramsAndExersicesMapper extends AbstractConverter<ProgramsAndExercises, ProgramsAndExersicesDto> {
    @Override
    protected ProgramsAndExersicesDto convert(ProgramsAndExercises programsAndExercises) {
        return ProgramsAndExersicesDto.builder()
                .exerciseDuration(programsAndExercises.getExerciseDuration())
                .exerciseId(programsAndExercises.getExerciseId())
                .programId(programsAndExercises.getProgramId())
                .numberOfRepetitions(programsAndExercises.getNumberOfRepetitions())
                .numberOfSets(programsAndExercises.getNumberOfSets())
                .build();
    }
}