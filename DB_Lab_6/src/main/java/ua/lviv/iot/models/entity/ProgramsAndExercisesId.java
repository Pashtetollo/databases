package ua.lviv.iot.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;


    @Embeddable
    public class ProgramsAndExercisesId implements Serializable {


        @Column(name = "programs_id")
        @NotNull
        private int programId;

        @Column(name = "exersices_id")
        @NotNull
        private int exerciseId;

        public ProgramsAndExercisesId(int programId, int exerciseId) {
            this.programId = programId;
            this.exerciseId = exerciseId;
        }

        public ProgramsAndExercisesId() {

        }

        @Override
    public int hashCode() {
        return Objects.hash(programId, exerciseId);
    }

    @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass())
                return false;

            ProgramsAndExercisesId that = (ProgramsAndExercisesId) o;
            return Objects.equals(programId, that.programId) &&
                    Objects.equals(exerciseId, that.exerciseId);
        }
}
