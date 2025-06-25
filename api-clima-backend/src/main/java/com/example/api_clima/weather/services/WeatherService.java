package com.example.api_clima.weather.services;

import com.example.api_clima.constants.IConstApi;
import com.example.api_clima.redis.RedisServices;
import com.example.api_clima.utils.StringFormatter;
import com.example.api_clima.weather.dto.WeatherDTO;
import com.example.api_clima.weather.dto.WeatherForecastDTO;
import com.example.api_clima.weather.Weather;
import com.example.api_clima.weather.wapper.WeatherMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class WeatherService implements IWeatherService, IConstApi, IConstRedisKey
{
    private final RedisServices<Weather> redis;
    private final RestTemplate restTemplate;

    public WeatherService(RedisServices<Weather> redis, RestTemplate restTemplate) {
        this.redis = redis;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Weather> findAllRedisOrFromApi(String city)
    {
        String cityFormat = StringFormatter.formatStringCapalize(city);

        String key = KEY_WEATHER + cityFormat;
        List<Weather> cacheList = redis.getAll(key, Weather.class);

        if(!cacheList.isEmpty())
        {
            return cacheList;
        }

        List<Weather> forecasts = fetchForecastWeatherFromApi(cityFormat);
        redis.save(cityFormat, forecasts);
        return forecasts;
    }

    @Override
    public Weather findRedisOrFromApi(String city)
    {
        String cityFormat = StringFormatter.formatStringCapalize(city);

        String key = KEY_FORECAST_WEATHER + cityFormat;
        Optional<Weather> optWeather = redis.get(key, Weather.class);

        if(optWeather.isPresent())
        {
            return optWeather.get();
        }

        Weather weather = fetchWeatherFromApi(cityFormat);
        redis.save(cityFormat, weather);
        return weather;
    }

    private List<Weather> fetchForecastWeatherFromApi(String city)
    {
        String urlFormatted = String.format(URL_FORECAST + KEY, city);


        WeatherForecastDTO weatherForecastDTO = restTemplate.getForObject(urlFormatted, WeatherForecastDTO.class);
        return Optional.ofNullable(weatherForecastDTO)
                .map(forecast -> WeatherMapper
                        .weatherForecastMapper(forecast, city, 5))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Previções do tempo não encontradas com a cidade: " + city));
    }


    private Weather fetchWeatherFromApi(String city)
    {
        String urlFormatted = String.format(URL_WEATHER + KEY, city);
        WeatherDTO weatherDTO = restTemplate.getForObject(urlFormatted, WeatherDTO.class);

        return Optional.ofNullable(weatherDTO)
                .map(w -> WeatherMapper.weatherMapper(w, city))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "clima atual não encontrado com a cidade: " + city));
    }
}