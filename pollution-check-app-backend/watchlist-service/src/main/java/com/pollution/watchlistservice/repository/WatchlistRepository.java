package com.pollution.watchlistservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pollution.watchlistservice.domain.Location;
import com.pollution.watchlistservice.domain.WatchlistedCity;

public interface WatchlistRepository extends CrudRepository<WatchlistedCity, String> {
    
    @Query("select w from Watchlist w where w.username = ?1")
    List<WatchlistedCity> findByUserEmail(String userEmail);

    @Query("select w from Watchlist w where w.username = ?1 and w.location = ?2")
    Optional<WatchlistedCity> findByUserEmailAndLocation(String userEmail, Location location);
}
