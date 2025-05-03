package com.nagarro.userservice.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
@Nonnull
public class UserAddress {
    private String city;
    private String street;
    private String pinCode;


}
