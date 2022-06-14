package com.pollution.watchlistservice.service;

import com.pollution.watchlistservice.domain.CityData;
import com.pollution.watchlistservice.exceptions.CityDataNotFoundException;
import java.util.List;

public interface WatchlistService {
  CityData addToWishlist(CityData requestData);

  void remove(Integer id) throws CityDataNotFoundException;

  List<CityData> findCityDataByUserEmail(String userEmail);

  CityData updateAqiUS(CityData requestData);
}
