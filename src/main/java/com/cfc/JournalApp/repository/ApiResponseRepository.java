package com.cfc.JournalApp.repository;

import com.cfc.JournalApp.entity.WeatherResponseEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ApiResponseRepository extends MongoRepository<WeatherResponseEntity, ObjectId> {
}
