package com.nagarro.userservice.mapper;


import com.nagarro.userservice.dtos.SignUpRequestDto;
import com.nagarro.userservice.dtos.UserDto;
import com.nagarro.userservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(uses = JsonNullableMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface UserMapper {
List<UserDto> UserToUserDto(List<User> userDetails);

UserDto userToUserDto(User user);

User userDtoToUser(UserDto userDto);

User UpdateUserFromDto(UserDto userDto, @MappingTarget User user);

User SignUpRequestDtoToUser(SignUpRequestDto signUpRequestDto);



}
