package com.example.api_clima.converter_dados;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class JsonMapper
{
    private final ObjectMapper mapper;

    public JsonMapper(ObjectMapper mapper)
    {
        this.mapper = mapper;
    }

    public <T> Optional<T> toObject(String json, Class<T> classType) throws JsonProcessingException
    {
        if(isInvalidJson(json))
        {
            return Optional.empty();
        }

        T obj = mapper.readValue(json, classType);
        return Optional.ofNullable(obj);
    }

    public <T> List<T> toObjectList(String json, Class<T> classType) throws JsonProcessingException
    {
        if(isInvalidJson(json))
        {
            return Collections.emptyList();
        }

        return mapper.readValue(json, mapper.getTypeFactory()
                .constructCollectionType(List.class, classType));
    }

    public <T> String toJson(T object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public boolean isInvalidJson(String json)
    {
        return json == null;
    }
}
