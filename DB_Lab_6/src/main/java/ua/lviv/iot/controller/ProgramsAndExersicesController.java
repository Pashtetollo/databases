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
    @ApiOperation(value = "Get  all exercises in programs", response = ProgramsAndExersicesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @GetMapping
    public ResponseEntity<List<ProgramsAndExersicesDto>> getProgramsAndExersicesList() {
        List<ProgramsAndExersicesDto> progAndExerList = new ArrayList<>();
        for (ProgramsAndExercises programsAndExersices: programsAndExersicesService.getAll()) {
            progAndExerList.add(modelMapper.map(programsAndExersices, ProgramsAndExersicesDto.class));
        }
        return new ResponseEntity<>(progAndExerList, HttpStatus.OK);
    }
    @ApiOperation(value = "Get  all exercises for specific program", response = ProgramsAndExersicesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @GetMapping(path = "/program/{id}")
    public ResponseEntity<List<ProgramsAndExersicesDto>> getExercisesListForProgram(@RequestParam int programId) {
        List<ProgramsAndExersicesDto> progAndExerList = new ArrayList<>();
        for (ProgramsAndExercises programsAndExersices: programsAndExersicesService.getAllByProgramId(programId)) {
            progAndExerList.add(modelMapper.map(programsAndExersices, ProgramsAndExersicesDto.class));
        }
        return new ResponseEntity<>(progAndExerList, HttpStatus.OK);
    }
    @ApiOperation(value = "exercise for program by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CoachDto.class),
            @ApiResponse(code = 404, message = "exercise for program record with such id not found")
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<ProgramsAndExersicesDto> getProgramsAndExercises(@PathVariable Integer id) {
        try {
            ProgramsAndExercises programsAndExercises = programsAndExersicesService.getById(id);
            return new ResponseEntity<>(modelMapper.map(programsAndExercises, ProgramsAndExersicesDto.class), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @ApiOperation(value = "create exercise for program")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "exercise for program created", response = CoachDto.class)
    })
    @PostMapping
    public ResponseEntity<ProgramsAndExersicesDto> newProgramsAndExercises(
            @RequestBody ProgramsAndExersicesDto programsAndExersicesDto) {
        ProgramsAndExercises newProgramsAndExercises = modelMapper.map(programsAndExersicesDto, ProgramsAndExercises.class);
        return new ResponseEntity<>(modelMapper.map(programsAndExersicesService.create(newProgramsAndExercises),
                ProgramsAndExersicesDto.class),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "update exercise for program record data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "exercise for program updated", response = ProgramsAndExersicesDto.class),
            @ApiResponse(code = 404, message = "exercise for program record with such id not found")
    })
    @PutMapping(path = "/{id}")
    public ResponseEntity<ProgramsAndExersicesDto> updateProgramsAndExercises(
            @RequestBody ProgramsAndExersicesDto programsAndExersicesDto, @PathVariable Integer id) {
        try {
            ProgramsAndExercises programsAndExersices = modelMapper.map(programsAndExersicesDto, ProgramsAndExercises.class);
            programsAndExersicesService.updateById(programsAndExersices, id);
            programsAndExersicesDto.setId(id);
            return new ResponseEntity<>(programsAndExersicesDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Delete program for exercise record with id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "", response = ProgramsAndExersicesDto.class),
            @ApiResponse(code = 404, message = "record with such id not found")
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CoachDto> deleteProgramsAndExercises(@PathVariable Integer id) {
        try {
            programsAndExersicesService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}