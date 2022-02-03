package ua.lviv.iot.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.models.DTO.UserDto;;
import ua.lviv.iot.models.entity.User;
import ua.lviv.iot.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<List<UserDto>> getUserList() {
        List<UserDto> userList = new ArrayList<>();
        for (User user: userService.getAll()) {
            userList.add(modelMapper.map(user, UserDto.class));
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer id) {
        try {
            User user = userService.getById(id);
            return new ResponseEntity<>(modelMapper.map(user, UserDto.class), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UserDto> newUser(@RequestBody UserDto userDto) {
        User newUser = User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .coach(userDto.getCoach())
                .program(userDto.getProgram())
                .build();
        return new ResponseEntity<>(modelMapper.map(userService.create(newUser), UserDto.class),
                HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer id) {
        try {
            User user = modelMapper.map(userDto, User.class);
            userService.updateById(user, id);
            userDto.setId(id);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Integer id) {
        try {
            userService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}