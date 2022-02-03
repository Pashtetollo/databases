package ua.lviv.iot.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.models.DTO.CoachDto;
import ua.lviv.iot.models.DTO.ProgramsAndExersicesDto;
import ua.lviv.iot.models.entity.ProgramsAndExercises;
import ua.lviv.iot.service.ProgramsAndExersicesService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/programsAndExersices")
public class ProgramsAndExersicesController {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    ProgramsAndExersicesService programsAndExersicesService;
    @ApiOperation(value = "Get  all coaches", response = CoachDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @GetMapping
    public ResponseEntity<List<ProgramsAndExersicesDto>> getCoachList() {
        List<ProgramsAndExersicesDto> progAndExerList = new ArrayList<>();
        for (ProgramsAndExercises programsAndExersices: programsAndExersicesService.getAll()) {
            progAndExerList.add(modelMapper.map(programsAndExersices, ProgramsAndExersicesDto.class));
        }
        return new ResponseEntity<>(progAndExerList, HttpStatus.OK);
    }
//    @ApiOperation(value = "Get  coach by id")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "OK", response = CoachDto.class),
//            @ApiResponse(code = 404, message = "Coach with such id not found")
//    })
//    @GetMapping(path = "/{id}")
//    public ResponseEntity<CoachDto> getCoach(@PathVariable Integer id) {
//        try {
//            Coach coach = coachService.getById(id);
//            return new ResponseEntity<>(modelMapper.map(coach, CoachDto.class), HttpStatus.OK);
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//    @ApiOperation(value = "create new coach")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "coach created", response = CoachDto.class)
//    })
//    @PostMapping
//    public ResponseEntity<CoachDto> newCoach(@RequestBody CoachDto coachDto) {
//        Coach newCoach = Coach.builder()
//                .name(coachDto.getName())
//                .surname(coachDto.getSurname())
//                .price(coachDto.getPrice())
//                .phoneNumber(coachDto.getPhoneNumber())
//                .build();
//        return new ResponseEntity<>(modelMapper.map(coachService.create(newCoach), CoachDto.class),
//                HttpStatus.CREATED);
//    }
//
//    @ApiOperation(value = "update coach data")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "coach updated", response = CoachDto.class),
//            @ApiResponse(code = 404, message = "Coach with such id not found")
//    })
//    @PutMapping(path = "/{id}")
//    public ResponseEntity<CoachDto> updateCoach(@RequestBody CoachDto coachDto, @PathVariable Integer id) {
//        try {
//            Coach coach = modelMapper.map(coachDto, Coach.class);
//            coachService.updateById(coach, id);
//            coachDto.setId(id);
//            return new ResponseEntity<>(coachDto, HttpStatus.OK);
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @ApiOperation(value = "Delete coach with id")
//    @ApiResponses(value = {
//            @ApiResponse(code = 204, message = "", response = CoachDto.class),
//            @ApiResponse(code = 404, message = "Coach with such id not found")
//    })
//    @DeleteMapping(path = "/{id}")
//    public ResponseEntity<CoachDto> deleteCoach(@PathVariable Integer id) {
//        try {
//            coachService.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

}