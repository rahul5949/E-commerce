package com.nagarro.userservice.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Nonnull
public class UserDetails extends BaseModel {
    private String email;
    private String userName;
    private String passWord;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Embedded
    private UserAddress address;



}
