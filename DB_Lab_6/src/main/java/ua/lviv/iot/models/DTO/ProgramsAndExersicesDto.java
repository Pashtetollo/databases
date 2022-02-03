package ua.lviv.iot.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.lviv.iot.models.entity.Exersice;
import ua.lviv.iot.models.entity.Program;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgramsAndExersicesDto {
    private Program programId;
    private Exersice exerciseId;
    private int exerciseDuration;
    private int numberOfRepetitions;
    private int numberOfSets;
}