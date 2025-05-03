package com.nagarro.userservice.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {
    private String token;
    private long expiresIn;
    private String accessToken;
    private long accessTokenExpiry;
    private String refreshToken;
    private long refreshTokenExpiry;
}
