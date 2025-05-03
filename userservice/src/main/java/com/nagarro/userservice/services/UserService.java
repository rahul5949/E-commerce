package com.nagarro.userservice.services;

import com.nagarro.userservice.dtos.UserDto;
import com.nagarro.userservice.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getSingleUser(Long id) throws UserNotFoundException;

    UserDto addNewUser(UserDto userDto);

    UserDto updateUser(Long id, UserDto userDto) throws UserNotFoundException;

    UserDto replaceUser(Long id, UserDto userDto) throws UserNotFoundException;

    void deleteUser(Long id);

}
