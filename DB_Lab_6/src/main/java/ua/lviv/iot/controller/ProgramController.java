package ua.lviv.iot.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.models.DTO.ProgramDto;
import ua.lviv.iot.models.entity.Program;
import ua.lviv.iot.service.ProgramService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/program")
public class ProgramController {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    ProgramService programService;
    @GetMapping
    public ResponseEntity<List<ProgramDto>> getProgramList() {
        List<ProgramDto> programList = new ArrayList<>();
        for (Program program: programService.getAll()) {
            programList.add(modelMapper.map(program, ProgramDto.class));
        }
        return new ResponseEntity<>(programList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProgramDto> getProgram(@PathVariable Integer id) {
        try {
            Program program = programService.getById(id);
            return new ResponseEntity<>(modelMapper.map(program, ProgramDto.class), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ProgramDto> newProgram(@RequestBody ProgramDto programDto) {
        Program newProgram = Program.builder()
                .name(programDto.getName())
                .build();
        return new ResponseEntity<>(modelMapper.map(programService.create(newProgram), ProgramDto.class),
                HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProgramDto> updateProgram(@RequestBody ProgramDto programDto, @PathVariable Integer id) {
        try {
            Program program = modelMapper.map(programDto, Program.class);
            programService.updateById(program, id);
            programDto.setId(id);
            return new ResponseEntity<>(programDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ProgramDto> deleteProgram(@PathVariable Integer id) {
        try {
            programService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}