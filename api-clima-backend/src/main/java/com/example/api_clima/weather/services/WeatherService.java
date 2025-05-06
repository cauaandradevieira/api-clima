package com.example.api_clima.weather.services;

import com.example.api_clima.constants.IConstApi;
import com.example.api_clima.utils.StringFormatter;
import com.example.api_clima.weather.model.dto.WeatherDTO;
import com.example.api_clima.weather.model.entity.Weather;
import com.example.api_clima.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class WeatherService implements IConstApi
{
    @Autowired
    private WeatherRepository weatherRepository;
    @Autowired
    private RestTemplate restTemplate;

    public Weather getWeather(String city)
    {
        try
        {
           Instant instant = Instant.now().minus(2, ChronoUnit.HOURS);

           return getValidWeather(city, instant);
        }
        catch (HttpClientErrorException e)
        {
            HttpStatus status = HttpStatus.resolve(e.getStatusCode().value());

            if(status == null)
            {
                throw new ResponseStatusException(e.getStatusCode() , "Code not is default");
            }
            throw new ResponseStatusException(status, status.name() + ":" + status.getReasonPhrase());
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Weather getValidWeather(String city, Instant instant)
    {
        
        return weatherRepository.findByValidWeather(city, instant)
                .orElseGet(() -> fetchAndSaveFromApi(city));
    }

    private Weather fetchAndSaveFromApi(String city)
    {
        String cityForApi = StringFormatter.formatStringUrlEncoder(city);

        String uri = String.format(URL + KEY, cityForApi);
        WeatherDTO weatherDto = restTemplate.getForObject(uri, WeatherDTO.class);

        if(weatherDto == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "city not found");
        }

        String cityForBd = StringFormatter.formatStringCapalize(city);
        Weather weather = new Weather(weatherDto, cityForBd);

        weatherRepository.save(weather);

        return weather;
    }
}
