package com.nagarro.productservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ManyToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    private boolean isEmailVerified;
    private List<Role> roles;




}
