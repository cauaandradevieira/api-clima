package com.example.api_clima.converter_dados;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class JsonMapper
{
    private final ObjectMapper mapper;

    public JsonMapper(ObjectMapper mapper)
    {
        this.mapper = mapper;
    }

    public <T> T toObject(String json, Class<T> classType) throws JsonProcessingException
    {
        return mapper.readValue(json, classType);
    }

    public <T> List<T> toObjectList(String json, Class<T> classType) throws JsonProcessingException
    {
        return mapper.readValue(json, mapper.getTypeFactory()
                .constructCollectionType(List.class, classType));
    }

    public <T> String toJson(T object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}
