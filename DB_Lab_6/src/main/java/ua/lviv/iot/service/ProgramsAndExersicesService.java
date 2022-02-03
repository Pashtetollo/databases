package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;
        import ua.lviv.iot.dao.ProgramRepository;
        import ua.lviv.iot.dao.ProgramsAndExersicesRepository;
        import ua.lviv.iot.models.entity.Coach;
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
}
