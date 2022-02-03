package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.iot.dao.MuscleGroupRepository;
import ua.lviv.iot.models.entity.MuscleGroup;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MuscleGroupService {
    @Autowired
    MuscleGroupRepository muscleGroupRepository;
    public List<MuscleGroup> getAll() {
        return muscleGroupRepository.findAll();
    }

    public MuscleGroup getById(Integer id) {
        return muscleGroupRepository.findById(id).get();
    }

    @Transactional
    public MuscleGroup create(MuscleGroup muscleGroup) {
        return muscleGroupRepository.save(muscleGroup);
    }

    @Transactional
    public void updateById(MuscleGroup muscleGroup, Integer id) {
        if(muscleGroupRepository.findById(id).isPresent()) {
            MuscleGroup muscleGroupToUpdate  = muscleGroupRepository.findById(id).get();
            muscleGroupToUpdate.setName(muscleGroup.getName());
        } else {
            throw new NoSuchElementException();
        }
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!muscleGroupRepository.existsById(id)) {
            throw new NoSuchElementException();
        }
        muscleGroupRepository.deleteById(id);
    }
}
