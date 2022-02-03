package ua.lviv.iot.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.models.DTO.ExersiceDto;
import ua.lviv.iot.models.entity.Exersice;
import ua.lviv.iot.service.ExersiceService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping(path = "/exercise")
public class ExerciseController {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    ExersiceService exersiceService;
    @GetMapping
    public ResponseEntity<List<ExersiceDto>> getExerciseList() {
        List<ExersiceDto> exerciseList = new ArrayList<>();
        for (Exersice exercise: exersiceService.getAll()) {
            exerciseList.add(modelMapper.map(exercise, ExersiceDto.class));
        }
        return new ResponseEntity<>(exerciseList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ExersiceDto> getExercise(@PathVariable Integer id) {
        try {
            Exersice exercise = exersiceService.getById(id);
            return new ResponseEntity<>(modelMapper.map(exercise, ExersiceDto.class), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ExersiceDto> newExercise(@RequestBody ExersiceDto exersiceDto) {
        Exersice newExercise = Exersice.builder()
                .name(exersiceDto.getName())
                .targeted_bodypart(exersiceDto.getTargeted_bodypart())
                .build();
        return new ResponseEntity<>(modelMapper.map(exersiceService.create(newExercise), ExersiceDto.class),
                HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ExersiceDto> updateCoach(@RequestBody ExersiceDto exersiceDto, @PathVariable Integer id) {
        try {
            Exersice exersice = modelMapper.map(exersiceDto, Exersice.class);
            exersiceService.updateById(exersice, id);
            exersiceDto.setId(id);
            return new ResponseEntity<>(exersiceDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ExersiceDto> deleteCoach(@PathVariable Integer id) {
        try {
            exersiceService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}