package com.pollution.apiservice.controller;

import com.pollution.apiservice.domain.*;
import com.pollution.apiservice.service.ApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ApiController {
    private ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/countries")
    public ResponseEntity<Country> getCountries(){
       Country countryData = apiService.getAllCountries();
        if(countryData == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(countryData);
    }

    @GetMapping("/states/{country}")
    public ResponseEntity<State> getCountries(@PathVariable("country") String country){
        State stateData = apiService.getAllStates(country);
        if(stateData == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(stateData);
    }

    @GetMapping("/cities/{country}/{state}")
    public ResponseEntity<City> getCities(@PathVariable("country") String country, @PathVariable("state") String state){
        City cityData = apiService.getAllCities(country, state);
        if(cityData == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(cityData);
    }

    @GetMapping("/nearest")
    public ResponseEntity<NearestCity> getNearestCityData(){
        NearestCity nearestCityData = apiService.getNearestCityData();
        if(nearestCityData == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(nearestCityData);
    }

    @GetMapping("/cityData/{country}/{state}/{city}")
    public ResponseEntity<CityAQI> getCityAqiByName(@PathVariable("country") String country, @PathVariable("state") String state, @PathVariable("city") String city){
        CityAQI cityAQIData = apiService.getCityDataByCityName(country, state, city);
        if(cityAQIData == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(cityAQIData);
    }
}
