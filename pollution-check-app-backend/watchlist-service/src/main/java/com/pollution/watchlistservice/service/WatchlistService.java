package com.pollution.watchlistservice.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.pollution.watchlistservice.domain.CityData;
import com.pollution.watchlistservice.exceptions.CityDataAlreadyExistsException;
import com.pollution.watchlistservice.exceptions.CityDataNotFoundException;

public interface WatchlistService {
    CityData addToWishlist(CityData requestData);

    void remove(Integer id) throws CityDataNotFoundException;

    List<CityData> findCityDataByUserEmail(String userEmail);

    CityData updateAqiUS(CityData requestData, Integer id);
}
