package ua.lviv.iot.models.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotNull
    private String name;
    @ManyToOne
    @JoinColumn(name = "coach_id", referencedColumnName = "id")
    private Coach coach;

    @ManyToOne
    @JoinColumn(name = "programs_id", referencedColumnName = "id")
    private Program program;

    @Column(name = "surname")
    @NotNull
    private String surname;

    @Override
    public String toString() {
        return String.format("id: %s, name: %s, surname: %s, coach:[ %s ], program: %s",
                id, name, surname, coach, program);
    }
}
