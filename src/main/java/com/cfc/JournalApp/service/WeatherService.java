package com.cfc.JournalApp.service;

import com.cfc.JournalApp.api.response.WeatherResponse;
import com.cfc.JournalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class WeatherService {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city) {

        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if(weatherResponse!= null){
            return weatherResponse;
        }
        else{


        // 1️⃣ Get API template from DB-loaded cache
        String apiTemplate = appCache.get(AppCache.Key.WEATHER_API);

        if (apiTemplate == null) {
            throw new RuntimeException("WEATHER_API not found in AppCache / DB");
        }

        // 2️⃣ Encode city (safe for spaces)
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);

        // 3️⃣ Build final API URL
        String finalApi = apiTemplate
                .replace("<apikey>", apiKey)
                .replace("<city>", encodedCity);

        // 4️⃣ Call external API
        ResponseEntity<WeatherResponse> response =
                restTemplate.getForEntity(finalApi, WeatherResponse.class);

        WeatherResponse body = response.getBody();
        if(body != null){
            redisService.set("weather_of_" + city, body, 300L);
        }

        if (body == null) {
            throw new RuntimeException("Empty response from Weather API");
        }

        return body;
    }
        }
}
