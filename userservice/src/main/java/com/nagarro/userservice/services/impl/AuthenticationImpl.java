package com.nagarro.userservice.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.userservice.dtos.*;
import com.nagarro.userservice.exceptions.*;
import com.nagarro.userservice.mapper.UserMapper;
import com.nagarro.userservice.model.User;
import com.nagarro.userservice.repository.TokenRepository;
import com.nagarro.userservice.repository.UserRepository;
import com.nagarro.userservice.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class AuthenticationImpl implements AuthenticationService {
    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

    private final UserMapper userMapper;

    private final KafkaTemplate<String,String> kafkaTemplate;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;
    private final RedisTokenService redisTokenService;
    private final ObjectMapper objectMapper;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) throws InvalidEmailOrPasswordException, JsonProcessingException {
        Optional<User> optionalUser = userRepository.findByEmail(loginRequestDto.getEmail());
        if(optionalUser.isEmpty()
                || !Objects.equals(optionalUser.get().getEmail(), loginRequestDto.getEmail())){
            throw new InvalidEmailOrPasswordException("Invalid Email");
        }

        if (!bCryptPasswordEncoder.matches(loginRequestDto.getPassword(), optionalUser.get().getHashedPassword())) {
            // throw password not matching exception
            throw new InvalidEmailOrPasswordException("Invalid Password");
        }
        /*authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );*/
        String jwtAccessToken = jwtService.generateToken(optionalUser.get());
        String jwtRefreshToken = jwtService.generateRefreshToken(optionalUser.get());
        redisTokenService.storeToken(jwtRefreshToken, jwtService.getRefreshExpiration());

        return LoginResponseDto.builder()
                .accessToken(jwtAccessToken)
                .accessTokenExpiry(jwtService.getExpirationTime())
                .refreshToken(jwtRefreshToken)
                .refreshTokenExpiry(jwtService.getRefreshExpiration())
                .build();


    }

    @Override
    public User signUp(SignUpRequestDto signUpRequestDto) throws UserEmailAlreadyExistsException, JsonProcessingException {
        Optional<User> optionalUser = userRepository.findByEmail(signUpRequestDto.getEmail());
        if(optionalUser.isPresent()){
            throw new UserEmailAlreadyExistsException("Email already taken ");
        }
        signUpRequestDto.setPassword(bCryptPasswordEncoder.encode(signUpRequestDto.getPassword()));
        User user = new User();
        user.setName(signUpRequestDto.getName());
        user.setEmail(signUpRequestDto.getEmail());
        user.setHashedPassword(signUpRequestDto.getPassword());
        SendEmailEventDto sendEmailEventDto = new SendEmailEventDto();
        sendEmailEventDto.setTo(signUpRequestDto.getEmail());
        sendEmailEventDto.setSubject("Welcome to our service");
        sendEmailEventDto.setBody("Thank you for signing up!");
        sendEmailEventDto.setFrom("rahul.srivastav5949@gmail.com");
        kafkaTemplate.send("send-email-topic", objectMapper.writeValueAsString(sendEmailEventDto));
    //    kafkaTemplate.flush();
       return userRepository.save(user);


    }

    @Override
    public void logout(LogoutRequestDto logoutRequestDto) throws TokenNotExistsOrAlreadyExpiredException {
        if (!redisTokenService.isTokenValid(logoutRequestDto.getToken())) {
            throw new TokenNotExistsOrAlreadyExpiredException("Token does not exist or invalid token");
        }
        redisTokenService.invalidateToken(logoutRequestDto.getToken());
    }

    @Override
    public LoginResponseDto refreshToken(String refreshToken) throws JsonProcessingException, InvalidTokenException {
        String username = jwtService.extractUsername(refreshToken);

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new InvalidTokenException("User not found"));

        if (!jwtService.isTokenValid(refreshToken, user)) {
            throw new InvalidTokenException("Refresh token is invalid or expired");
        }

        if (!redisTokenService.isTokenValid(refreshToken)) {
            throw new InvalidTokenException("Refresh token has been revoked or doesn't exist");
        }

        String newAccessToken = jwtService.generateToken(user);

        return LoginResponseDto.builder()
                .accessToken(newAccessToken)
                .accessTokenExpiry(jwtService.getExpirationTime())
                .refreshToken(refreshToken)
                .refreshTokenExpiry(jwtService.getRefreshExpiration())
                .build();
    }


    @Override
    public UserDto validateToken(String token) throws UserNotFoundException, InvalidTokenException {
        if (!jwtService.isTokenValid(token)) {
            throw new InvalidTokenException("Token is invalid or expired");
        }

        String username = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return UserDto.from(user);
    }


}
