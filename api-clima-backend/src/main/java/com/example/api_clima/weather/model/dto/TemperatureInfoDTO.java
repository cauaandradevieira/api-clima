package com.example.api_clima.weather.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TemperatureInfoDTO(@JsonProperty("temp")Integer temp,
                                 @JsonProperty("humidity") Integer humidity,
                                 @JsonProperty("temp_min") Integer tempMin,
                                 @JsonProperty("temp_max") Integer tempMax) {
}
