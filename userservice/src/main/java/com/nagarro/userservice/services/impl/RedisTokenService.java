package com.nagarro.userservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisTokenService {

    private final StringRedisTemplate redisTemplate;

    private static final String PREFIX = "refresh_token:";

    public void storeToken(String token, long expiryInMillis) {
        redisTemplate.opsForValue().set(PREFIX + token, "valid", Duration.ofMillis(expiryInMillis));
    }

    public boolean isTokenValid(String token) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(PREFIX + token));
    }

    public void invalidateToken(String token) {
        redisTemplate.delete(PREFIX + token);
    }
}
