package ua.lviv.iot.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "exersices")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exersice {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "targeted_bodypart", referencedColumnName = "id")
    private MuscleGroup targeted_bodypart;

    @Override
    public String toString() {
        return String.format("id: %s, name: %s, targeted_bodypart: %s", id, name, targeted_bodypart);
    }
}
