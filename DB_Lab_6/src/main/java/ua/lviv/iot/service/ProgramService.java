package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.iot.dao.ProgramRepository;
import ua.lviv.iot.models.entity.Coach;
import ua.lviv.iot.models.entity.Program;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class ProgramService {
    @Autowired
    ProgramRepository programRepository;
    public List<Program> getAll() {
        return programRepository.findAll();
    }

    public Program getById(Integer id) {
        return programRepository.findById(id).get();
    }

    @Transactional
    public Program create(Program program) {
        return programRepository.save(program);
    }

    @Transactional
    public void updateById(Program program, Integer id) {
        if(programRepository.findById(id).isPresent()) {
            Program programToUpdate = programRepository.findById(id).get();
            programToUpdate.setName(program.getName());;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!programRepository.existsById(id)) {
            throw new NoSuchElementException();
        }
        programRepository.deleteById(id);
    }
}
