package com.example.api_clima.weather.exception;

public class NotFoundWeatherException extends RuntimeException {
    public NotFoundWeatherException(String message) {
        super(message);
    }
}
