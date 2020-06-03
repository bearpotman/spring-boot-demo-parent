package com.spring.boot.demo.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @Auth wangJun
 * @Version V1.0.1
 * @Description
 * @Date 2020/06/03
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 使用 String 序列化方式，序列化key
        redisTemplate.setKeySerializer(RedisSerializer.string());
        // 使用 JSON 序列化方式（库是 Jackson ），序列化value
        redisTemplate.setValueSerializer(RedisSerializer.json());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }
}
