package ua.lviv.iot.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.lviv.iot.models.entity.MuscleGroup;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExersiceDto {
    private Integer id;
    private String name;
    private MuscleGroup targeted_bodypart;
}
