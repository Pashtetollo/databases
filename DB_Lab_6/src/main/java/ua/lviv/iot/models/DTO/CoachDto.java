package ua.lviv.iot.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.lviv.iot.models.entity.Program;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoachDto {
    private Integer id;
    private String name;
    private String surname;
    private String phoneNumber;
    private double price;
}
