package com.cfc.JournalApp.cache;

import com.cfc.JournalApp.entity.WeatherResponseEntity;
import com.cfc.JournalApp.repository.ApiResponseRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum Key {
        WEATHER_API
    }

    @Autowired
    private ApiResponseRepository apiResponseRepository;

    private Map<String, String> appCache;

    @PostConstruct
    public void init() {
        appCache = new HashMap<>();

        List<WeatherResponseEntity> all = apiResponseRepository.findAll();
        for (WeatherResponseEntity entity : all) {
            appCache.put(entity.getKey(), entity.getValue());
        }

        System.out.println("AppCache loaded keys: " + appCache.keySet());
    }

    // ✅ SAFE GETTER
    public String get(Key key) {
        return appCache.get(key.name());
    }

}
