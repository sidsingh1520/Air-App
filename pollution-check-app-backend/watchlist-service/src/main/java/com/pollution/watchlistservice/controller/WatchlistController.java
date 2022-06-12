package com.pollution.watchlistservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pollution.watchlistservice.domain.CityData;
import com.pollution.watchlistservice.dto.AddToWatchlistDto;
import com.pollution.watchlistservice.dto.RemoveFromWatchlistDto;
import com.pollution.watchlistservice.exceptions.CityDataNotFoundException;
import com.pollution.watchlistservice.service.WatchlistService;

@RequestMapping("/wishlist")
@RestController
public class WatchlistController {
    
    private WatchlistService service;

    public WatchlistController(WatchlistService service) {
        this.service = service;
    }


    @GetMapping("/byUserid/{userId}")
    public List<CityData> findAll(@PathVariable  long userId) throws Exception {

        List<CityData> response = service.listWatchlistByUserId(userId);
        return response;
    }


    @PostMapping("/add")
    public CityData add(@RequestBody AddToWatchlistDto requestData) throws Exception {
        CityData response= service.addToWishlist(requestData);
        return response;
    }

    @DeleteMapping("/delete/{userId}")
    public void remove(@PathVariable long userId, @RequestBody String city) throws CityDataNotFoundException {
        RemoveFromWatchlistDto request = new RemoveFromWatchlistDto();
        request.setUserId(userId);
        request.setCity(city);
       service.remove(request);
    }
}
