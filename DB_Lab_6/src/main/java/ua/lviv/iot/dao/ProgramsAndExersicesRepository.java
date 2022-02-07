package ua.lviv.iot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.models.entity.Program;
import ua.lviv.iot.models.entity.ProgramsAndExercises;

import java.util.List;


@Repository
public interface ProgramsAndExersicesRepository extends JpaRepository<ProgramsAndExercises, Integer> {
    @Query(value = "SELECT * FROM programs_has_exercises as pe where pe.program_id=:programId", nativeQuery = true)
    List<ProgramsAndExercises> getAllByProgramId(int programId);
}
