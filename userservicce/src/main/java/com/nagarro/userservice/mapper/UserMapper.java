package com.nagarro.userservice.mapper;


import com.nagarro.userservice.dto.UserDetailsDto;
import com.nagarro.userservice.model.UserDetails;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(uses = JsonNullableMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface UserMapper {
List<UserDetailsDto> UserDetailsToUserDetailsDto(List<UserDetails> userDetails);

UserDetailsDto userDetailsToUserDetailsDto(UserDetails userDetails);

UserDetails userDetailsDtoToUserDetails(UserDetailsDto userDetailsDto);

UserDetails UpdateUserDetailsFromDto(UserDetailsDto userDetailsDto, @MappingTarget UserDetails userDetails);


}
