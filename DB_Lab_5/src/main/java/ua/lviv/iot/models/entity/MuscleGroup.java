package ua.lviv.iot.models.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "muscle_groups")
public class MuscleGroup {
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "name")
        @NotNull
        private String name;

        @Override
        public String toString() {
            return String.format("id: %s, name: %s", id, name);
        }

}
