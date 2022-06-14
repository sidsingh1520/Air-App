package com.pollution.watchlistservice.controller;

import java.util.List;

import com.pollution.watchlistservice.dto.CityDataDto;
import com.pollution.watchlistservice.exceptions.CityDataAlreadyExistsException;
import com.pollution.watchlistservice.service.WatchlistServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.pollution.watchlistservice.domain.CityData;
import com.pollution.watchlistservice.exceptions.CityDataNotFoundException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/watchlist")
@CrossOrigin(origins = "http://localhost:4200")
public class WatchlistController {
    
    private WatchlistServiceImpl service;

    public WatchlistController(WatchlistServiceImpl service) {
        this.service = service;
    }


    @GetMapping("/{userEmail}")
    public ResponseEntity<List<CityData>> findAll(@PathVariable String userEmail) throws CityDataNotFoundException {

        List<CityData> response = service.findCityDataByUserEmail(userEmail);
        if(response.isEmpty()){
            throw new CityDataNotFoundException("The data for this city doesn't exist");
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping({"", "/"})
    public ResponseEntity<CityData> add(@RequestBody CityDataDto requestData) throws CityDataAlreadyExistsException {
        CityData cityData = new CityData(requestData);
        CityData response= service.addToWishlist(cityData);
        if(response == null){
            throw new CityDataAlreadyExistsException("The data for this city already exists.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CityData> remove(@PathVariable Integer id) throws CityDataNotFoundException {
        service.remove(id);
       return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PutMapping({"", "/"})
    public ResponseEntity<CityData> updateAqiUS(@RequestBody CityDataDto requestData) throws CityDataNotFoundException {
        CityData cityData = new CityData(requestData);
        CityData response = service.updateAqiUS(cityData);
        if(response == null){
            throw new CityDataNotFoundException("The data for this city doesn't exist");
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
