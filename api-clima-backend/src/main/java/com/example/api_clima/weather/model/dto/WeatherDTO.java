package com.example.api_clima.weather.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherDTO(@JsonProperty("main") TemperatureInfoDTO temperatureInfo,
                         @JsonProperty("weather") List<WeatherDescriptionsDTO> weatherDescriptions,
                         @JsonProperty("sys") WeatherCountryDTO weatherCountry)
{}
