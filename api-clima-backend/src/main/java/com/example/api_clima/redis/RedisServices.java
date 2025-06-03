package com.example.api_clima.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/redis")
public class RedisServices
{
    private final CacheManager redisCache;

    @Autowired
    public RedisServices(CacheManager redisCache) {
        this.redisCache = redisCache;
    }

    @Cacheable(value = "weatherForecast", key = "#key")
    @GetMapping()
    public List<Integer> teste(@RequestParam String key)
    {
        System.out.println("metodo do teste");
        return IntStream.rangeClosed(1, 100000)
                .boxed()
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/cacheManager")
    public List<Integer> testeWithCacheManager(@RequestParam String key, @RequestParam String name)
    {
        return Optional.ofNullable(redisCache.getCache(name))
                .map(cache -> (List<Integer>) cache.get(key, List.class))
                .orElseGet(Collections::emptyList);
    }
}
