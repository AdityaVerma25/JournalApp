package com.cfc.JournalApp.controller;
import com.cfc.JournalApp.api.response.WeatherResponse;
import com.cfc.JournalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public ResponseEntity<WeatherResponse> getWeather(
            @RequestParam String city
    ) {
        WeatherResponse response = weatherService.getWeather(city);
        return ResponseEntity.ok(response);
    }
}
