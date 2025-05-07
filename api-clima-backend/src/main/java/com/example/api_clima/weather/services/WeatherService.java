package com.example.api_clima.weather.services;

import com.example.api_clima.constants.IConstApi;
import com.example.api_clima.utils.StringFormatter;
import com.example.api_clima.weather.model.dto.WeatherDTO;
import com.example.api_clima.weather.model.entity.Weather;
import com.example.api_clima.weather.repository.WeatherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherService implements IConstApi
{
    @Autowired
    private WeatherRepository weatherRepository;
    @Autowired
    private RestTemplate restTemplate;

    public Weather findValidWeather(String city)
    {
        try
        {
           Instant instant = Instant.now().minus(2, ChronoUnit.MINUTES);
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

    public List<Weather> findAll()
    {
        return weatherRepository.findAll();
    }


    private Weather getValidWeather(String city, Instant instant)
    {
        System.out.println("Nome da cidade parametro do metodo getValidWeather: " + city);
        System.out.println("-----------------------------------------------------------------");

        return weatherRepository.findByValidWeather(city, instant)
                .orElseGet(() -> fetchAndSaveFromApi(city));
    }

    @Transactional
    private Weather fetchAndSaveFromApi(String city)
    {
        System.out.println("Nome da cidade parametro do metodo fetchAndSaveFromApi: " + city);
        System.out.println("-----------------------------------------------------------------");

        String cityForApi = StringFormatter.formatStringCapalize(city);

        System.out.println("Nome da cidade metodo StringFormatter: " + cityForApi);
        System.out.println("-----------------------------------------------------------------");

        String uri = String.format(URL + KEY, cityForApi);

        System.out.println("Nome da uri api do metodo fetchAndSaveFromApi (String Format): " + uri);
        System.out.println("-----------------------------------------------------------------");

        WeatherDTO weatherDto = restTemplate.getForObject(uri, WeatherDTO.class);

        System.out.println("Nome do WeatherDTO do metodo fetchAndSaveFromApi (String Format): " + weatherDto);
        System.out.println("-----------------------------------------------------------------");

        if(weatherDto == null)
        {
            System.out.println("Entrou na condição se weatherDTO e null");
            System.out.println("-----------------------------------------------------------------");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "city not found");
        }

        String cityForBd = StringFormatter.formatStringCapalize(city);

        System.out.println("Nome da cidade em capalize do metodo StringFormatter: " + cityForBd);
        System.out.println("-----------------------------------------------------------------");

        Optional<Weather> weatherOptional = weatherRepository.findByCity(cityForBd);
        weatherOptional.ifPresent(w -> w.setDateUpdatedAt(Instant.now()));

        Weather weather = weatherOptional
                .orElse(new Weather(weatherDto,cityForBd));

        System.out.println("Obj weather: " + weather);
        System.out.println("-----------------------------------------------------------------");

        weatherRepository.save(weather);

        return weather;
    }
}
