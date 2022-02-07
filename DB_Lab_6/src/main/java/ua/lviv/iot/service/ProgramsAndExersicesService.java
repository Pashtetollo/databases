package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dao.ProgramsAndExersicesRepository;
import ua.lviv.iot.models.entity.Program;
import ua.lviv.iot.models.entity.ProgramsAndExercises;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class ProgramsAndExersicesService {
    @Autowired
    ProgramsAndExersicesRepository programsAndExersicesRepository;
    public List<ProgramsAndExercises> getAll() {
        return programsAndExersicesRepository.findAll();
    }

    public List<ProgramsAndExercises> getAllByProgramId(int programId) {
        return programsAndExersicesRepository.getAllByProgramId(programId);
    }

    public ProgramsAndExercises getById(Integer id) {
        return programsAndExersicesRepository.findById(id).get();
    }

    public ProgramsAndExercises create(ProgramsAndExercises programsAndExercises) {
        programsAndExercises.setId(0);
        return programsAndExersicesRepository.save(programsAndExercises);
    }

    public void updateById(ProgramsAndExercises programsAndExercises, Integer id) {
        if(programsAndExersicesRepository.findById(id).isPresent()) {
            ProgramsAndExercises programsAndExercisesToUpdate = programsAndExersicesRepository.findById(id).get();
            programsAndExercisesToUpdate.setExercise(programsAndExercises.getExercise());
            programsAndExercisesToUpdate.setProgram(programsAndExercises.getProgram());
            programsAndExercisesToUpdate.setExerciseDuration(programsAndExercises.getExerciseDuration());
            programsAndExercisesToUpdate.setNumberOfRepetitions(programsAndExercises.getNumberOfRepetitions());
            programsAndExercisesToUpdate.setNumberOfSets(programsAndExercises.getNumberOfSets());
        } else {
            throw new NoSuchElementException();
        }
    }

    public void deleteById(Integer id) {
        if (!programsAndExersicesRepository.existsById(id)) {
            throw new NoSuchElementException();
        }
        programsAndExersicesRepository.deleteById(id);
    }
}
