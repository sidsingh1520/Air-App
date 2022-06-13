package com.pollution.watchlistservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.pollution.watchlistservice.domain.Location;
import com.pollution.watchlistservice.domain.WatchlistedCity;
// import com.pollution.watchlistservice.exceptions.CityDataAlreadyExistsException;
// import com.pollution.watchlistservice.exceptions.CityDataNotFoundException;
import com.pollution.watchlistservice.repository.WatchlistRepository;

public class WatchlistServiceImpl implements WatchlistService{

    private WatchlistRepository repository;

    public WatchlistServiceImpl(WatchlistRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean addCity(WatchlistedCity city) {
        
        try {
			String email1 = city.getUserEmail();
            Location location1 = city.getLocation();
			List<WatchlistedCity> list = (List<WatchlistedCity>) repository.findAll();
			for (WatchlistedCity temp : list) {
				if((temp.getUserEmail().equalsIgnoreCase(email1)) && (Arrays.equals(temp.getLocation().getCoordinates(), location1.getCoordinates()))) {
					return true;
				}
			}
			repository.save(city);
			return true;
		} catch (Exception e) {
			return false;
		}
    }

    @Override
    public boolean removeCity(String userEmail, Location location) {

        try {
			Optional<WatchlistedCity> optional = repository.findByUserEmailAndLocation(userEmail, location);
			if(optional.isPresent()){
                repository.delete(optional.get());
                return true;
            }
            return false;	
		}catch (Exception e) {
			return false;
		}
    }

    @Override
    public List<WatchlistedCity> getList(String userEmail) {
        return (List<WatchlistedCity>) repository.findByUserEmail(userEmail);
    }

    
}
