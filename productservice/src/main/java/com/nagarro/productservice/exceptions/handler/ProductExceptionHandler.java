package com.nagarro.productservice.exceptions.handler;

import com.nagarro.productservice.dto.ErrorDto;
import com.nagarro.productservice.exceptions.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
@Slf4j
public class ProductExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException ex){
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
