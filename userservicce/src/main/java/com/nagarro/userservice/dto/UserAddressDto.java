package com.nagarro.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserAddressDto {
    private String city;
    private String street;
    @JsonProperty("pincode")
    private String pinCode;
}
