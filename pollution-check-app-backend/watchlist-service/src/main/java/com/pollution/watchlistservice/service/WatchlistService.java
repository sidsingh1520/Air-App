package com.pollution.watchlistservice.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.pollution.watchlistservice.domain.CityData;
import com.pollution.watchlistservice.dto.AddToWatchlistDto;
import com.pollution.watchlistservice.dto.RemoveFromWatchlistDto;
import com.pollution.watchlistservice.exceptions.CityDataAlreadyExistsException;
import com.pollution.watchlistservice.exceptions.CityDataNotFoundException;

@Validated
public interface WatchlistService {
    CityData addToWishlist(AddToWatchlistDto requestData) throws  CityDataAlreadyExistsException;

    void remove(RemoveFromWatchlistDto requestData) throws CityDataNotFoundException;

    List<CityData> listWatchlistByUserId(long userId) throws CityDataNotFoundException;
}
