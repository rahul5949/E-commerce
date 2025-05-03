package com.nagarro.productservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {
    // Configuration for Redis can be added here
    // For example, setting up a RedisTemplate or configuring connection properties
    // This is a placeholder for future Redis configuration

    @Bean
        public RedisTemplate<String,Object>  createRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // Placeholder for Redis connection configuration
        return redisTemplate;
    }
}
