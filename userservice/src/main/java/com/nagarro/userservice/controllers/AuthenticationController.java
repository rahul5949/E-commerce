package com.nagarro.userservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nagarro.userservice.dtos.*;
import com.nagarro.userservice.exceptions.*;
import com.nagarro.userservice.model.User;
import com.nagarro.userservice.services.impl.AuthenticationImpl;
import io.micrometer.common.lang.NonNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationImpl authentication;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) throws InvalidEmailOrPasswordException, JsonProcessingException {
        return ResponseEntity.ok(authentication.login(loginRequestDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) throws UserEmailAlreadyExistsException, JsonProcessingException{
        User savedUser = authentication.signUp(signUpRequestDto);
        UserDto userDto = UserDto.from(savedUser);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto logoutRequestDto) throws TokenNotExistsOrAlreadyExpiredException {
        authentication.logout(logoutRequestDto);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PostMapping("/validate/{token}")
    public UserDto validateToken(@PathVariable("token") @NonNull String token) throws UserNotFoundException, InvalidTokenException {
        return authentication.validateToken(token);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refreshToken(@RequestBody Map<String, String> body) throws JsonProcessingException, InvalidTokenException {
        return ResponseEntity.ok(authentication.refreshToken(body.get("refreshToken")));
    }


}
