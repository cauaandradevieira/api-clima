package com.example.api_clima.weather.repository;

import com.example.api_clima.weather.model.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WeatherRepository extends JpaRepository< Weather, UUID >
{
    Optional<Weather> findByCity(String city);

    @Query("SELECT w FROM Weather w WHERE w.city = :city AND w.dateUpdatedAt >= :date")
    Optional<Weather> findByValidWeather(@Param("city")String city,
                                         @Param("date")Instant date);

}
