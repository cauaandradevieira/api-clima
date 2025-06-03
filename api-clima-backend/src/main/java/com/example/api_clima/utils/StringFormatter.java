package com.example.api_clima.utils;

import org.springframework.util.StringUtils;
import java.util.Arrays;
import java.util.stream.Collectors;

public class StringFormatter
{

    public static String formatStringCapalize(String city)
    {
        String [] string = city.toLowerCase().trim().split(" "); // pegando a cidade e removendo os espaços não desejados e colocando tudo para minusculo
        return Arrays.stream(string)                    // estar fazendo um fluxo de cada elemento da lista
            .map(StringUtils::capitalize)               // estar deixando todos os elementos do array com a primeira letra maiuscula
            .collect(Collectors.joining(" "));  // estar fazendo de array virar uma string
    }

    /*public static String formatStringUrlEncoder(String city)
    {
        city = formatStringCapalize(city);
        return URLEncoder.encode(city, StandardCharsets.UTF_8);
    }*/
}
