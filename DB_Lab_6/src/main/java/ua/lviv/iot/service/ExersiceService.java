package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.iot.dao.ExersiceRepository;
import ua.lviv.iot.models.entity.Exersice;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ExersiceService {
    @Autowired
    ExersiceRepository exersiceRepository;
    public List<Exersice> getAll() {
        return exersiceRepository.findAll();
    }

    public Exersice getById(Integer id) {
        return exersiceRepository.findById(id).get();
    }

    @Transactional
    public Exersice create(Exersice exersice) {
        return exersiceRepository.save(exersice);
    }

    @Transactional
    public void updateById(Exersice exersice, Integer id) {
        if(exersiceRepository.findById(id).isPresent()) {
            Exersice exersiceToUpdate = exersiceRepository.findById(id).get();
            exersiceToUpdate.setName(exersice.getName());
            exersiceToUpdate.setTargeted_bodypart(exersice.getTargeted_bodypart());
            throw new NoSuchElementException();
        }
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!exersiceRepository.existsById(id)) {
            throw new NoSuchElementException();
        }
        exersiceRepository.deleteById(id);
    }
}
