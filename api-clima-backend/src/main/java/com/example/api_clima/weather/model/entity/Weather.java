package com.example.api_clima.weather.model.entity;

import com.example.api_clima.weather.model.dto.CountryInfoDTO;
import com.example.api_clima.weather.model.dto.TemperatureInfoDTO;
import com.example.api_clima.weather.model.dto.WeatherDTO;
import com.example.api_clima.weather.model.dto.WeatherDescriptionDTO;

import java.time.Instant;

public class Weather
{
    private String description;
    private String country;

    private Integer humidity;
    private Integer temp;
    private Integer tempMin;
    private Integer tempMax;

    private String city;

    private Integer timestampInSeconds;

    public Weather() {
    }

    public Weather(WeatherDTO weatherDTO, String city)
    {
        this.city = city;
        this.description = weatherDTO.weatherDescription().getFirst().description();
        this.country = weatherDTO.country().country();
        this.humidity = weatherDTO.temperatureInfo().humidity();
        this.temp = weatherDTO.temperatureInfo().temp();
        this.tempMin = weatherDTO.temperatureInfo().tempMin();
        this.tempMax = weatherDTO.temperatureInfo().tempMax();
        this.timestampInSeconds = weatherDTO.date();
    }

    public Weather(WeatherDTO weatherDTO, CountryInfoDTO countryInfoDTO, String city)
    {
        this.city = city;
        this.description = weatherDTO.weatherDescription().getFirst().description();
        this.country =countryInfoDTO.country();
        this.humidity = weatherDTO.temperatureInfo().humidity();
        this.temp = weatherDTO.temperatureInfo().temp();
        this.tempMin = weatherDTO.temperatureInfo().tempMin();
        this.tempMax = weatherDTO.temperatureInfo().tempMax();
        this.timestampInSeconds = weatherDTO.date();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String name) {
        this.city = name;
    }

    public String getCountry() {return country;}

    public void setCountry(String country) {this.country = country;}

    public Integer getTempMin() {return tempMin;}

    public void setTempMin(Integer tempMin) {this.tempMin = tempMin;}

    public Integer getTempMax() {return tempMax;}

    public void setTempMax(Integer tempMax) {this.tempMax = tempMax;}

    public Integer getTimestampInSeconds() {
        return timestampInSeconds;
    }

    public void setTimestampInSeconds(Integer timestampInSeconds) {
        this.timestampInSeconds = timestampInSeconds;
    }

}
