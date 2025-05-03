package com.nagarro.userservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nagarro.userservice.dtos.*;
import com.nagarro.userservice.exceptions.*;
import com.nagarro.userservice.model.User;

import java.util.concurrent.ExecutionException;

public interface AuthenticationService {
    LoginResponseDto login(LoginRequestDto loginRequestDto) throws InvalidEmailOrPasswordException, JsonProcessingException;

    User signUp(SignUpRequestDto signUpRequestDto) throws UserEmailAlreadyExistsException, JsonProcessingException, ExecutionException, InterruptedException;

    void logout(LogoutRequestDto logoutRequestDto) throws TokenNotExistsOrAlreadyExpiredException;

    LoginResponseDto refreshToken(String refreshToken) throws JsonProcessingException, InvalidTokenException;
    UserDto validateToken(String token) throws UserNotFoundException, InvalidTokenException;

}
