package com.example.api_clima.weather.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherDTO(@JsonProperty("main") TemperatureInfoDTO temperatureInfo,
                         @JsonProperty("weather") List<WeatherDescriptionDTO> weatherDescription,
                         @JsonProperty("sys") CountryInfoDTO country,
                         @JsonProperty("dt") Integer date)
{}
