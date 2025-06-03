package com.example.api_clima.weather.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherForecastDTO(@JsonProperty("list") List<WeatherDTO> listForecast,
                                 @JsonProperty("city") CountryInfoDTO country) {
}
