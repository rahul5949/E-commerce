package com.nagarro.userservice.services.impl;

import com.nagarro.userservice.dtos.UserDto;
import com.nagarro.userservice.exceptions.UserNotFoundException;
import com.nagarro.userservice.mapper.UserMapper;
import com.nagarro.userservice.model.User;
import com.nagarro.userservice.repository.UserRepository;
import com.nagarro.userservice.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;






    public List<UserDto> getAllUsers(){
        List<User> userDetails = userRepository.findAll();
        return userMapper.UserToUserDto(userDetails);
    }

    @Override
    public UserDto getSingleUser(Long id) throws UserNotFoundException {
        Optional<User> optionalUserDetails = userRepository.findById(id);

        if(optionalUserDetails.isEmpty()){
            throw new UserNotFoundException("User Not found ");
        }
        return userMapper.userToUserDto(optionalUserDetails.get()) ;
    }

    @Override
    public UserDto addNewUser(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        return userMapper.userToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) throws UserNotFoundException {
        Optional<User> optionalUserDetails = userRepository.findById(id);
        if(optionalUserDetails.isEmpty()){
            throw  new UserNotFoundException("The User does not exist with id " + id);
        }
        return userMapper.userToUserDto(userRepository.save(userMapper.UpdateUserFromDto(userDto, optionalUserDetails.get())));
    }

    @Override
    public UserDto replaceUser(Long id, UserDto userDto) throws UserNotFoundException {
        Optional<User> optionalUserDetails = userRepository.findById(id);
        if(optionalUserDetails.isEmpty()){
            throw  new UserNotFoundException("The User does not exist with id " + id);
        }
        return userMapper.userToUserDto(userRepository.save(
                userMapper.userDtoToUser(userDto)
        ));
    }


    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
