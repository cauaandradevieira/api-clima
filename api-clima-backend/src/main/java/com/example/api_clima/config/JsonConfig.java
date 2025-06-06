package com.example.api_clima.config;

import com.example.api_clima.converter_dados.JsonMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class JsonConfig
{
    @Bean
    public ObjectMapper objectMapper()
    {
        return new ObjectMapper();
    }
    @Bean
    public JsonMapper jsonMapper(ObjectMapper objectMapper)
    {
        return new JsonMapper(objectMapper);
    }

    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
}
