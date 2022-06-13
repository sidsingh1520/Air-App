package com.pollution.watchlistservice.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.pollution.watchlistservice.domain.Location;
import com.pollution.watchlistservice.domain.WatchlistedCity;
// import com.pollution.watchlistservice.exceptions.CityDataAlreadyExistsException;
// import com.pollution.watchlistservice.exceptions.CityDataNotFoundException;

@Validated
public interface WatchlistService {
    // WatchlistedCity addToWatchlist(AddToWatchlistDto requestData) throws  CityDataAlreadyExistsException;

    // void remove(RemoveFromWatchlistDto requestData) throws CityDataNotFoundException;

    // List<WatchlistedCity> listWatchlistByUserEmail(String userEmail) throws CityDataNotFoundException;

    public List<WatchlistedCity> getList(String userEmail);
	
	public boolean addCity(WatchlistedCity city);
	
	public boolean removeCity(String userEmail, Location location);
}
