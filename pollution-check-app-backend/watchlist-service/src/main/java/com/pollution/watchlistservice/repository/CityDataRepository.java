package com.pollution.watchlistservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pollution.watchlistservice.domain.CityData;

public interface CityDataRepository extends JpaRepository<CityData, Integer> {
    
    List<CityData> findByUserEmail(String userEmail);

    Optional<CityData> findByUserEmailAndCityAndState(String userEmail, String city, String state);
}
