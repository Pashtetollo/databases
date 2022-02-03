package ua.lviv.iot.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.lviv.iot.models.entity.Coach;
import ua.lviv.iot.models.entity.Program;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private String name;
    private String surname;
    private Coach coach;
    private Program program;
}
