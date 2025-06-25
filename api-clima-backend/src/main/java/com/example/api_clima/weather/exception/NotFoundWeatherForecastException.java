package com.example.api_clima.weather.exception;

public class NotFoundWeatherForecastException extends RuntimeException
{
    public NotFoundWeatherForecastException(String message) {
        super(message);
    }
}
