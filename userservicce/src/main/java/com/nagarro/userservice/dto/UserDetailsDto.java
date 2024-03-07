package com.nagarro.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nagarro.userservice.model.BaseModel;
import com.nagarro.userservice.model.UserAddress;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDetailsDto extends BaseModel {
    private String email;
    @JsonProperty("username")
    private String userName;
    @JsonProperty("password")
    private String passWord;
    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("lastname")
    private String lastName;
    @JsonProperty("phone")
    private String phoneNumber;
    private UserAddressDto address;

}
