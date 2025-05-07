package com.example.api_clima.weather.model.entity;

import com.example.api_clima.weather.model.dto.WeatherDTO;
import jakarta.persistence.*;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "weather_tb")
public class Weather
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;
    private String icon;
    private String country;

    private Integer humidity;
    private Integer temp;
    private Integer tempMin;
    private Integer tempMax;

    private Instant dateCreatedAt;
    private Instant dateUpdatedAt;

    private String city;

    public Weather(WeatherDTO weatherDTO, String city)
    {
        this.country = weatherDTO.weatherCountry().country();
        this.description = weatherDTO.weatherDescriptions().getFirst().description();
        this.icon = weatherDTO.weatherDescriptions().getFirst().icon();
        this.humidity = weatherDTO.temperatureInfo().humidity();
        this.temp =  weatherDTO.temperatureInfo().temp();
        this.tempMin = weatherDTO.temperatureInfo().tempMin();
        this.tempMax = weatherDTO.temperatureInfo().tempMax();
        this.city = city;
        this.dateCreatedAt = Instant.now();
        this.dateUpdatedAt = Instant.now();
    }

    public Weather() {}

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", dateCreatedAt=" + dateCreatedAt +
                ", dateUpdatedAt=" + dateUpdatedAt +
                ", city='" + city + '\'' +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public Instant getDateCreatedAt() {
        return dateCreatedAt;
    }

    public void setDateCreatedAt(Instant dateCreatedAt) {
        this.dateCreatedAt = dateCreatedAt;
    }

    public Instant getDateUpdatedAt() {
        return dateUpdatedAt;
    }

    public void setDateUpdatedAt(Instant dateUpdatedAt) {
        this.dateUpdatedAt = dateUpdatedAt;
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
}
