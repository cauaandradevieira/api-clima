package com.example.api_clima.weather.controller;

import com.example.api_clima.weather.model.entity.Weather;
import com.example.api_clima.weather.services.WeatherService;

import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@NoArgsConstructor
@RestController
@RequestMapping("/weather")
public class WeatherController
{
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/{cidade}")
    
    public ResponseEntity<?> findWeather(@PathVariable String cidade)
    {
        Weather weather = weatherService.getWeather(cidade);
        return ResponseEntity.ok(weather);
    }
}
