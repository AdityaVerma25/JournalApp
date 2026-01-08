package com.cfc.JournalApp.api.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherResponse {
    private Current current;

    // ===================== Current =====================
    @Data
    public static class Current {

        private int temperature;


        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;


        private int pressure;
        private int humidity;
        private int feelslike;

        private int visibility;
    }


}
