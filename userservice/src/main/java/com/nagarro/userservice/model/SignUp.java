package com.nagarro.userservice.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
@Nonnull
public class SignUp {
    private String email;
    private String name;
    private String hashPassword;
}
