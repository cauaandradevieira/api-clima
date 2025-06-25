package com.example.api_clima.exceptions_handlers;

import com.example.api_clima.convert_data.exception.FailedToDeserializeException;
import com.example.api_clima.convert_data.exception.FailedToSerializeException;
import com.example.api_clima.weather.exception.NotFoundWeatherException;
import com.example.api_clima.weather.exception.NotFoundWeatherForecastException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(FailedToDeserializeException.class)
    public ResponseEntity<ErrorResponse> handleFailedToDeserializeException(FailedToDeserializeException ex)
    {
        log.error("Error to deserializer: ", ex);
        ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(FailedToSerializeException.class)
    public ResponseEntity<ErrorResponse> handleFailedToSerializeException(FailedToSerializeException ex)
    {
        log.error("Error to serializer: ", ex);
        ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(NotFoundWeatherException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundWeather(NotFoundWeatherException ex)
    {
        log.error("Error to fetch weather from api ", ex);
        ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(NotFoundWeatherForecastException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundWeatherForecast(NotFoundWeatherForecastException ex)
    {
        log.error("Error to fetch forecast weather from api ", ex);
        ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
