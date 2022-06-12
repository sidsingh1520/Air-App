package com.pollution.watchlistservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("watchlist")
public class CityData {
    
    @Id
    private long id;

    private long userId;
    private String city;
    private String state;
    private String country;
    private Pollution pollution;
}
