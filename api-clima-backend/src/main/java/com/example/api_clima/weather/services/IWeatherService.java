package com.example.api_clima.weather.services;

import com.example.api_clima.weather.Weather;

import java.util.List;

public interface IWeatherService
{
    List<Weather> findAllRedisOrFromApi(String city);
    Weather findRedisOrFromApi(String city);
}
