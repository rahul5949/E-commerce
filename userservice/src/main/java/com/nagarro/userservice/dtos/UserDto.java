package com.nagarro.userservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nagarro.userservice.model.BaseModel;
import com.nagarro.userservice.model.Role;
import com.nagarro.userservice.model.User;
import jakarta.persistence.ManyToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class UserDto extends BaseModel {
    private String name;
    private String email;
    private boolean isEmailVerified;
    @ManyToMany
    private List<Role> roles;


    public static UserDto from(User user) {
        if (user == null) return null;

        UserDto userDto = new UserDto();
        userDto.email = user.getEmail();
        userDto.name = user.getName();
        userDto.roles = user.getRoles();
        userDto.isEmailVerified = user.isEmailVerified();

        return userDto;
    }

}
