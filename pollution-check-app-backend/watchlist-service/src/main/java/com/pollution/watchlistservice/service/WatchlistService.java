package com.pollution.watchlistservice.service;

import java.util.List;

import com.pollution.watchlistservice.domain.Location;
import com.pollution.watchlistservice.domain.WatchlistedCity;

public interface WatchlistService {

    public List<WatchlistedCity> getListByEmail(String userEmail);
	
	public boolean addCity(WatchlistedCity city);
	
	public boolean removeCity(String userEmail, Location location);
}
