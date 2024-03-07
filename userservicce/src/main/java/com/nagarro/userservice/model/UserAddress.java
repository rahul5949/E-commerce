package com.nagarro.userservice.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Embeddable
@Nonnull
public class UserAddress {
    private String city;
    private String street;
    private String pinCode;


}
