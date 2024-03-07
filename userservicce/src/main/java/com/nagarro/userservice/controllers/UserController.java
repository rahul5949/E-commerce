package com.nagarro.userservice.controllers;

import com.nagarro.userservice.dto.UserDetailsDto;
import com.nagarro.userservice.exceptions.UserNotFoundException;
import com.nagarro.userservice.model.UserDetails;
import com.nagarro.userservice.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping()
    public ResponseEntity<List<UserDetailsDto>> getAllUsers(){
        return ResponseEntity.ok(userServiceImpl.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsDto> getSingleUser(@PathVariable Long id) throws UserNotFoundException {
        return  ResponseEntity.ok(userServiceImpl.getSingleUser(id));
    }

    @PostMapping()
    public ResponseEntity<UserDetailsDto> addNewUser(@RequestBody UserDetailsDto userDetailsDto){
        return ResponseEntity.ok(userServiceImpl.addNewUser(userDetailsDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetailsDto> replaceUser(@PathVariable Long id, @RequestBody UserDetailsDto userDetailsDto) throws UserNotFoundException {
        return ResponseEntity.ok(userServiceImpl.replaceUser(id, userDetailsDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDetailsDto> updateUser(@PathVariable Long id, @RequestBody UserDetailsDto userDetailsDto) throws UserNotFoundException {
        return ResponseEntity.ok(userServiceImpl.updateUser(id, userDetailsDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userServiceImpl.deleteUser(id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }


}
