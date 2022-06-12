package com.pollution.watchlistservice.service;

import java.util.List;

import com.pollution.watchlistservice.domain.CityData;
import com.pollution.watchlistservice.dto.AddToWatchlistDto;
import com.pollution.watchlistservice.dto.RemoveFromWatchlistDto;
import com.pollution.watchlistservice.exceptions.CityDataAlreadyExistsException;
import com.pollution.watchlistservice.exceptions.CityDataNotFoundException;

public class WatchlistServiceImpl implements WatchlistService{

    @Override
    public CityData addToWishlist(AddToWatchlistDto requestData) throws CityDataAlreadyExistsException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CityData> listWatchlistByUserId(long userId) throws CityDataNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void remove(RemoveFromWatchlistDto requestData) throws CityDataNotFoundException {
        // TODO Auto-generated method stub
        
    }
    
}
