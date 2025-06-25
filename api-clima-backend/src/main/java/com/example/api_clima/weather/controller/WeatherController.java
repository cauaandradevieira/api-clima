package com.example.api_clima.weather.controller;

import com.example.api_clima.weather.Weather;
import com.example.api_clima.weather.exception.NotFoundWeatherException;
import com.example.api_clima.weather.exception.NotFoundWeatherForecastException;
import com.example.api_clima.weather.services.IWeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController
{
    private final IWeatherService weatherService;

    public WeatherController(IWeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/forecast/{city}")
    public List<Weather> findWeatherForecast(@PathVariable String city)
    {
        try
        {
            return weatherService.findAllRedisOrFromApi(city);
        }
        catch (HttpClientErrorException ex)
        {
            throw new NotFoundWeatherForecastException("Previções de " + city + " não encontrada.");
        }
    }

    @GetMapping("/{city}")
    public Weather findWeather(@PathVariable  String city)
    {
        try
        {
            return weatherService.findRedisOrFromApi(city);
        }
        catch (HttpClientErrorException ex)
        {
            throw new NotFoundWeatherException("Clima atual de " + city + " não encontrada.");
        }
    }
}
