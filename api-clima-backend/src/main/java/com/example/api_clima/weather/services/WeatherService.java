package com.example.api_clima.weather.services;

import com.example.api_clima.constants.IConstApi;
import com.example.api_clima.converter_dados.JsonMapper;
import com.example.api_clima.redis.RedisServices;
import com.example.api_clima.weather.model.dto.WeatherDTO;
import com.example.api_clima.weather.model.dto.WeatherForecastDTO;
import com.example.api_clima.weather.model.entity.Weather;
import com.example.api_clima.weather.wapper.WeatherMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherService implements IConstApi
{
    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);
    @Autowired
    private RedisServices<Weather> redis;
    @Autowired
    private RestTemplate restTemplate;

    public List<Weather> findAllRedisOrFromApi(String city) throws JsonProcessingException
    {

        List<Weather> cacheList = redis.findAllByCache(city, Weather.class);

        if(!cacheList.isEmpty())
        {
            return cacheList;
        }

        List<Weather> forecasts = seachForecastWeatherFromApi(city);
        redis.save(city, forecasts);
        return forecasts;
    }

    public Weather findRedisOrFromApi(String city) throws JsonProcessingException
    {
        Optional<Weather> optWeather = redis.findByCache(city, Weather.class);

        if(optWeather.isPresent())
        {
            return optWeather.get();
        }

        Weather weather = seachWeatherFromApi(city);
        redis.save(city, weather);
        return weather;
    }

    private List<Weather> seachForecastWeatherFromApi(String city)
    {
        String urlFormatted = String.format(URL_FORECAST + KEY, city);

        WeatherForecastDTO weatherForecastDTO = restTemplate.getForObject(urlFormatted, WeatherForecastDTO.class);
        return Optional.ofNullable(weatherForecastDTO)
                .map(forecast -> WeatherMapper
                        .weatherForecastMapper(forecast, city, 5))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: result from api not found."));
    }


    private Weather seachWeatherFromApi(String city)
    {
        String urlFormatted = String.format(URL_WEATHER + KEY, city);
        WeatherDTO weatherDTO = restTemplate.getForObject(urlFormatted, WeatherDTO.class);

        return Optional.ofNullable(weatherDTO)
                .map(w -> WeatherMapper.weatherMapper(w, city))
                .orElseThrow(() -> new RuntimeException("conserte aaqui"));
    }
}