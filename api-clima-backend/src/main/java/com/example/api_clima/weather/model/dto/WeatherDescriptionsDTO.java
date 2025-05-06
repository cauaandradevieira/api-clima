package com.example.api_clima.weather.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherDescriptionsDTO(@JsonProperty("description") String description, @JsonProperty("icon") String icon) {
}
