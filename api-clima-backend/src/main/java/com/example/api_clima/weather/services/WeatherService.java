package com.example.api_clima.weather.services;

import com.example.api_clima.constants.IConstApi;
import com.example.api_clima.redis.RedisServices;
import com.example.api_clima.weather.model.dto.WeatherDTO;
import com.example.api_clima.weather.model.dto.WeatherForecastDTO;
import com.example.api_clima.weather.model.entity.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WeatherService implements IConstApi
{
    @Autowired
    private RedisServices redis;
    @Autowired
    private RestTemplate restTemplate;

    public List<Weather> seachForecastWeatherFromApi(String city)
    {
        String urlFormatted = String.format(URL_FORECAST + KEY, city);
        WeatherForecastDTO weatherForecastDTO = restTemplate.getForObject(urlFormatted, WeatherForecastDTO.class);
        return weatherForecastDTO.listForecast().stream()
                .map(w -> new Weather(w,weatherForecastDTO.country(),city))
                .limit(5)
                .toList();
    }

    public Weather seachWeatherFromApi(String city)
    {
        String urlFormatted = String.format(URL_WEATHER + KEY, city);
        WeatherDTO weatherDTO = restTemplate.getForObject(urlFormatted, WeatherDTO.class);
        return new Weather(weatherDTO, city);
    }
}