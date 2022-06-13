package com.pollution.watchlistservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pollution.watchlistservice.domain.Location;
import com.pollution.watchlistservice.domain.WatchlistedCity;
// import com.pollution.watchlistservice.exceptions.CityDataNotFoundException;
import com.pollution.watchlistservice.service.WatchlistService;

@RequestMapping("/api/watchlist")
@CrossOrigin(value = "*")
@RestController
public class WatchlistController {
    
    private WatchlistService service;

    public WatchlistController(WatchlistService service) {
        this.service = service;
    }


    @GetMapping
	public ResponseEntity<?> getList(@RequestParam("userEmail") String userEmail) {
		try {
			return new ResponseEntity<List<WatchlistedCity>>(service.getList(userEmail),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>("no",HttpStatus.CONFLICT);
		}
	}

    @PostMapping
	public ResponseEntity<?> addCity(@RequestBody WatchlistedCity city) {
		if(service.addCity(city)) {
			return new ResponseEntity<String>("ok", HttpStatus.CREATED);
		}
		else return new ResponseEntity<String>("no", HttpStatus.CONFLICT);
	}

    @DeleteMapping("id")
	public ResponseEntity<String> deleteallData(@PathVariable String userEmail, @RequestBody Location location){
		service.removeCity(userEmail, location);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
