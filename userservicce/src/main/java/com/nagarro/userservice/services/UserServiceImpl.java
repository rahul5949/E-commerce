package com.nagarro.userservice.services;

import com.nagarro.userservice.dto.UserDetailsDto;
import com.nagarro.userservice.exceptions.UserNotFoundException;
import com.nagarro.userservice.mapper.UserMapper;
import com.nagarro.userservice.model.UserDetails;
import com.nagarro.userservice.repository.UserRepository;
import com.nagarro.userservice.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public List<UserDetailsDto> getAllUsers(){
        List<UserDetails> userDetails = userRepository.findAll();
        /*List<UserDetails> finalUserDetails = userDetails
                .stream()
                .filter(i -> !i.isDeleted())
                .toList();*/
        return userMapper.UserDetailsToUserDetailsDto(userDetails);
    }

    @Override
    public UserDetailsDto getSingleUser(Long id) throws UserNotFoundException {
        Optional<UserDetails> optionalUserDetails = userRepository.findById(id);

        if(optionalUserDetails.isEmpty()){
            throw new UserNotFoundException("User Not found ");
        }
        return userMapper.userDetailsToUserDetailsDto(optionalUserDetails.get()) ;
    }

    @Override
    public UserDetailsDto addNewUser(UserDetailsDto userDetailsDto) {
        UserDetails userDetails = userMapper.userDetailsDtoToUserDetails(userDetailsDto);
        return userMapper.userDetailsToUserDetailsDto(userRepository.save(userDetails));
    }

    @Override
    public UserDetailsDto updateUser(Long id, UserDetailsDto userDetailsDto) throws UserNotFoundException {
        Optional<UserDetails> optionalUserDetails = userRepository.findById(id);
        if(optionalUserDetails.isEmpty()){
            throw  new UserNotFoundException("The User does not exist with id " + id);
        }
        return userMapper.userDetailsToUserDetailsDto(userRepository.save(userMapper.UpdateUserDetailsFromDto(userDetailsDto, optionalUserDetails.get())));
    }

    @Override
    public UserDetailsDto replaceUser(Long id, UserDetailsDto userDetailsDto) throws UserNotFoundException {
        Optional<UserDetails> optionalUserDetails = userRepository.findById(id);
        if(optionalUserDetails.isEmpty()){
            throw  new UserNotFoundException("The User does not exist with id " + id);
        }
        return userMapper.userDetailsToUserDetailsDto(userRepository.save(
                userMapper.userDetailsDtoToUserDetails(userDetailsDto)
        ));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
