package com.example.api_clima.redis;

import com.example.api_clima.converter_dados.JsonMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RedisServices <T>
{
    private final RedisTemplate<String,String> redis;
    private final JsonMapper jsonMapper;

    @Autowired
    public RedisServices(RedisTemplate<String, String> redis, JsonMapper jsonMapper)
    {
        this.redis = redis;
        this.jsonMapper = jsonMapper;
    }

    public List<T> findAllByCache(String key, Class<T> tClass) throws JsonProcessingException
    {
        String json = redis.opsForValue().get(key);
        return jsonMapper.toObjectList(json,tClass);
    }

    public Optional<T> findByCache(String key, Class<T> tClass) throws JsonProcessingException
    {
        String json = redis.opsForValue().get(key);
        return jsonMapper.toObject(json, tClass);
    }

    public void save(String key, T object) throws JsonProcessingException
    {
        String json = jsonMapper.toJson(object);
        redis.opsForValue().set(key,json);
    }

    public void save(String key, List<T> object) throws JsonProcessingException
    {
        String json = jsonMapper.toJson(object);
        redis.opsForValue().set(key,json);
    }
}
