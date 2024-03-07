package com.nagarro.userservice.services.impl;

import com.nagarro.userservice.dto.UserDetailsDto;
import com.nagarro.userservice.exceptions.UserNotFoundException;
import com.nagarro.userservice.model.UserDetails;

import java.util.List;

public interface UserService {
    List<UserDetailsDto> getAllUsers();

    UserDetailsDto getSingleUser(Long id) throws UserNotFoundException;

    UserDetailsDto addNewUser(UserDetailsDto userDetailsDto);

    UserDetailsDto updateUser(Long id, UserDetailsDto userDetailsDto) throws UserNotFoundException;

    UserDetailsDto replaceUser(Long id, UserDetailsDto userDetailsDto) throws UserNotFoundException;

    void deleteUser(Long id);

}
