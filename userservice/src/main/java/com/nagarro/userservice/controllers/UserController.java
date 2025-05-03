package com.nagarro.userservice.controllers;

import com.nagarro.userservice.dtos.UserDto;
import com.nagarro.userservice.exceptions.UserNotFoundException;
import com.nagarro.userservice.services.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userServiceImpl.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Long id) throws UserNotFoundException {
        return ResponseEntity.ok(userServiceImpl.getSingleUser(id));
    }

    @PostMapping()
    public ResponseEntity<UserDto> addNewUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userServiceImpl.addNewUser(userDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> replaceUser(@PathVariable Long id, @RequestBody UserDto userDto) throws UserNotFoundException {
        return ResponseEntity.ok(userServiceImpl.replaceUser(id, userDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) throws UserNotFoundException {
        return ResponseEntity.ok(userServiceImpl.updateUser(id, userDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userServiceImpl.deleteUser(id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }








}
