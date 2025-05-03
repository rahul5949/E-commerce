package com.nagarro.productservice.commmons;

import com.nagarro.productservice.dto.UserDto;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationCommons {
    private RestTemplate restTemplate;
    public AuthenticationCommons(RestTemplate restTemplate){

        this.restTemplate = restTemplate;
    }
    public UserDto validateToken(String token){
      ResponseEntity<UserDto> userDtoResponse =
              restTemplate.postForEntity("http://localhost:8081/users/validate", token, UserDto.class);
        return userDtoResponse.getBody() != null ? userDtoResponse.getBody() : null ;
    }
}
