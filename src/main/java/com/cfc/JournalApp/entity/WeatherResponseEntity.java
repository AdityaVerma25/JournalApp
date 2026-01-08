package com.cfc.JournalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("api_entity")
@Data
@NoArgsConstructor
public class WeatherResponseEntity {
    private String key;
    private String value;
}
