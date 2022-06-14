package com.pollution.watchlistservice.service;

import java.util.List;
import java.util.Optional;

import com.pollution.watchlistservice.domain.CityData;
import com.pollution.watchlistservice.exceptions.CityDataAlreadyExistsException;
import com.pollution.watchlistservice.exceptions.CityDataNotFoundException;
import com.pollution.watchlistservice.repository.CityDataRepository;
import org.springframework.stereotype.Service;

@Service
public class WatchlistServiceImpl implements WatchlistService{
    private CityDataRepository cityDataRepository;

    public WatchlistServiceImpl(CityDataRepository cityDataRepository) {
        this.cityDataRepository = cityDataRepository;
    }

    @Override
    public CityData addToWishlist(CityData requestData){
        Optional<CityData> cityDataOptional = cityDataRepository.findByUserEmailAndCityAndState(requestData.getUserEmail(), requestData.getCity(), requestData.getState());
        if(cityDataOptional.isPresent()){
            return null;
        }
        return cityDataRepository.save(requestData);

    }

    @Override
    public void remove(Integer id) throws CityDataNotFoundException {
        Optional<CityData> cityDataOptional = cityDataRepository.findById(id);
        if(cityDataOptional.isEmpty()){
            throw new CityDataNotFoundException("The data for this city doesn't exist");
        }
        cityDataRepository.deleteById(id);
    }

    @Override
    public List<CityData> findCityDataByUserEmail(String userEmail) {
        return cityDataRepository.findByUserEmail(userEmail);
    }

    @Override
    public CityData updateAqiUS(CityData requestData, Integer id) {
        Optional<CityData> existingCityDataOptional = cityDataRepository.findById(id);
        CityData existingCityData;
        if(existingCityDataOptional.isPresent()){
            existingCityData = existingCityDataOptional.get();
            existingCityData.setAqiUS(requestData.getAqiUS());
            return cityDataRepository.save(existingCityData);
        }
        return null;
    }
}
