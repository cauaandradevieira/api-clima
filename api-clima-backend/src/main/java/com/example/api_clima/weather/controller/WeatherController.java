package com.example.api_clima.weather.controller;

import com.example.api_clima.weather.model.entity.Weather;
import com.example.api_clima.weather.services.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController
{
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/forecast/{city}")
    public List<Weather> findWeatherForecast(@PathVariable String city) throws JsonProcessingException {
        return weatherService.findAllRedisOrFromApi(city);
    }

    @GetMapping("/{city}")
    public Weather findWeather(@PathVariable  String city) throws Exception {
        return weatherService.findRedisOrFromApi(city);
    }
}
