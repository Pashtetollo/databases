package ua.lviv.iot.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Embeddable
@Entity
@Data
@IdClass(ProgramsAndExercisesId.class)
@Table(name = "programs_has_exercises")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramsAndExercises {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "programs_id", referencedColumnName = "id")
    @Id
    private Program programId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exersices_id", referencedColumnName = "id")
    @Id
    private Exersice exerciseId;

    @Column(name = "exercise_duration")
    private int exerciseDuration;

    @Column(name = "number_of_repetitions")
    private int numberOfRepetitions;

    @Column(name = "number_of_sets")
    private int numberOfSets;


}
