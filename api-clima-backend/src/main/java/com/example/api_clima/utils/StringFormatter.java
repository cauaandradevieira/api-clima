package com.example.api_clima.utils;

import org.springframework.util.StringUtils;
import java.util.Arrays;
import java.util.stream.Collectors;

public class StringFormatter
{

    public static String formatStringCapalize(String city)
    {
        String [] string = city.toLowerCase().trim().split(" "); // rio|de|janeiro
        return Arrays.stream(string)
            .map(StringUtils::capitalize)
            .collect(Collectors.joining(" ")); // Rio De Janeiro
    }

    /*public static String formatStringUrlEncoder(String city)
    {
        city = formatStringCapalize(city);
        return URLEncoder.encode(city, StandardCharsets.UTF_8);
    }*/
}
