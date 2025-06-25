package com.example.api_clima.weather.wapper;

import com.example.api_clima.weather.dto.WeatherDTO;
import com.example.api_clima.weather.dto.WeatherForecastDTO;
import com.example.api_clima.weather.Weather;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherMapper
{
    public static List<Weather> weatherForecastMapper(WeatherForecastDTO weatherForecastDTO, String city, int counter)
    {
        return weatherForecastDTO.listForecast().stream()
                .map(w -> new Weather(w,weatherForecastDTO.country(),city))
                .collect(Collectors.groupingBy(w -> Instant.ofEpochSecond(w.getTimestampInSeconds())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()))
                .values().stream()
                .limit(counter)
                .map(List::getFirst)
                .sorted(Comparator.comparing(Weather::getTimestampInSeconds))
                .toList();
    }

    public static Weather weatherMapper(WeatherDTO weatherDTO, String city)
    {
        return new Weather(weatherDTO, city);
    }

}
