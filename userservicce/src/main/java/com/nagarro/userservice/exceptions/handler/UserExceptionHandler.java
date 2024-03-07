package com.nagarro.userservice.exceptions.handler;

import com.nagarro.userservice.dto.ErrorDto;
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

}
