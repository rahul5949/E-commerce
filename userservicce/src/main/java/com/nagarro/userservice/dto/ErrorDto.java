package com.nagarro.userservice.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class ErrorDto {
    private HttpStatus status;
    private int errorCode;
    private String errorMessage;
    private Instant timeStamp;
    private List<String> errors;
}
