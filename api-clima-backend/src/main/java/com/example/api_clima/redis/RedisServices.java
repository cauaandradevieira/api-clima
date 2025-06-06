package com.example.api_clima.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisServices<T>
{
    private RedisTemplate<String,Object> redis;

    @Autowired
    public RedisServices(RedisTemplate<String, Object> redis) {
        this.redis = redis;
    }

    public List<T> findByCache(String key, Class<T> classType)
    {
        return (List<T>) redis.opsForValue().get(key);
    }
}
