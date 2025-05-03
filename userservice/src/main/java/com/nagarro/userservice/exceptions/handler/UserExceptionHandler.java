package com.nagarro.userservice.exceptions.handler;

import com.nagarro.userservice.dtos.ErrorDto;
import com.nagarro.userservice.exceptions.InvalidEmailOrPasswordException;
import com.nagarro.userservice.exceptions.InvalidTokenException;
import com.nagarro.userservice.exceptions.UserEmailAlreadyExistsException;
import com.nagarro.userservice.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
@Slf4j
public class UserExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException ex){
        log.warn(ex.getMessage());
        var errorDto = ErrorDto.builder()
                .status(HttpStatus.NOT_FOUND)
                .errorCode(404)
                .errorMessage(ex.getMessage())
                .timeStamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);

    }

    @ExceptionHandler(UserEmailAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handleUserEmailAlreadyExistsException(UserEmailAlreadyExistsException ex){
        log.warn(ex.getMessage());
        var errorDto = ErrorDto.builder()
                .status(HttpStatus.BAD_REQUEST)
                .errorCode(400)
                .errorMessage(ex.getMessage())
                .timeStamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(InvalidEmailOrPasswordException.class)
    public ResponseEntity<ErrorDto> handleInvalidEmailOrPasswordException(InvalidEmailOrPasswordException ex){
        log.warn(ex.getMessage());
        var errorDto = ErrorDto.builder()
                .status(HttpStatus.NOT_FOUND)
                .errorCode(404)
                .errorMessage(ex.getMessage())
                .timeStamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public  ResponseEntity<ErrorDto> handleInvalidTokenException(InvalidTokenException ex){
        log.warn(ex.getMessage());
        var errorDto = ErrorDto.builder()
                .status(HttpStatus.NOT_FOUND)
                .errorCode(404)
                .errorMessage(ex.getMessage())
                .timeStamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

}
