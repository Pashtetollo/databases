package ua.lviv.iot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.models.entity.ProgramsAndExercises;
import ua.lviv.iot.models.entity.ProgramsAndExercisesId;

@Repository
public interface ProgramsAndExersicesRepository extends JpaRepository<ProgramsAndExercises, ProgramsAndExercisesId> {
}
