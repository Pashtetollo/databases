package ua.lviv.iot.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "programs_has_exercises")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramsAndExercises {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exersice exercise;

    @Column(name = "exercise_duration")
    private int exerciseDuration;

    @Column(name = "number_of_repetitions")
    private int numberOfRepetitions;

    @Column(name = "number_of_sets")
    private int numberOfSets;


}
