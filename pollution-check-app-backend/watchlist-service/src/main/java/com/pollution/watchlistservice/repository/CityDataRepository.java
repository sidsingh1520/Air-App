package com.pollution.watchlistservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pollution.watchlistservice.domain.CityData;

public interface CityDataRepository extends MongoRepository<CityData, String> {
    
    List<CityData> findByUserId(long userId);
    Optional<CityData> findByUserIdAndCity(long userId, String city);

}
