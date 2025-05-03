package com.nagarro.userservice.exceptions;

public class UserEmailAlreadyExistsException extends Exception {
    public UserEmailAlreadyExistsException(String message) {
        super(message);
    }
}
