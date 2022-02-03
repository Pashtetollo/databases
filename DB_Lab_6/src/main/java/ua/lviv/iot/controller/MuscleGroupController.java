package ua.lviv.iot.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.models.DTO.MuscleGroupDto;
import ua.lviv.iot.models.entity.MuscleGroup;
import ua.lviv.iot.service.MuscleGroupService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/muscleGroup")
public class MuscleGroupController {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    MuscleGroupService muscleGroupService;
    @GetMapping
    public ResponseEntity<List<MuscleGroupDto>> getMuscleGroupList() {
        List<MuscleGroupDto> muscleGroupList = new ArrayList<>();
        for (MuscleGroup muscleGroup: muscleGroupService.getAll()) {
            muscleGroupList.add(modelMapper.map(muscleGroup, MuscleGroupDto.class));
        }
        return new ResponseEntity<>(muscleGroupList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MuscleGroupDto> getMuscleGroup(@PathVariable Integer id) {
        try {
            MuscleGroup muscleGroup = muscleGroupService.getById(id);
            return new ResponseEntity<>(modelMapper.map(muscleGroup, MuscleGroupDto.class), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<MuscleGroupDto> newCoach(@RequestBody MuscleGroupDto muscleGroupDto) {
        MuscleGroup newMuscleGroup = MuscleGroup.builder()
                .name(muscleGroupDto.getName())
                .build();
        return new ResponseEntity<>(modelMapper.map(muscleGroupService.create(newMuscleGroup), MuscleGroupDto.class),
                HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<MuscleGroupDto> updateCoach(@RequestBody MuscleGroupDto muscleGroupDto, @PathVariable Integer id) {
        try {
            MuscleGroup muscleGroup = modelMapper.map(muscleGroupDto, MuscleGroup.class);
            muscleGroupService.updateById(muscleGroup, id);
            muscleGroupDto.setId(id);
            return new ResponseEntity<>(muscleGroupDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<MuscleGroupDto> deleteCoach(@PathVariable Integer id) {
        try {
            muscleGroupService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}