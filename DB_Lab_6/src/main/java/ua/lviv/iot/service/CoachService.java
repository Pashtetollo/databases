package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.iot.dao.CoachRepository;
import ua.lviv.iot.models.entity.Coach;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CoachService {
    @Autowired
    CoachRepository coachRepository;
    public List<Coach> getAll() {
        return coachRepository.findAll();
    }

    public Coach getById(Integer id) {
        return coachRepository.findById(id).get();
    }

    @Transactional
    public Coach create(Coach coach) {
        return coachRepository.save(coach);
    }

    @Transactional
    public void updateById(Coach coach, Integer id) {
        if(coachRepository.findById(id).isPresent()) {
            Coach coachToUpdate = coachRepository.findById(id).get();
            coachToUpdate.setName(coach.getName());
            coachToUpdate.setSurname(coach.getSurname());
            coachToUpdate.setPhoneNumber(coach.getPhoneNumber());
            coachToUpdate.setPrice(coach.getPrice());
        } else {
            throw new NoSuchElementException();
        }
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!coachRepository.existsById(id)) {
            throw new NoSuchElementException();
        }
        coachRepository.deleteById(id);
    }
}
