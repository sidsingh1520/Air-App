package com.pollution.watchlistservice.domain;

import com.pollution.watchlistservice.dto.CityDataDto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "watchlist")
public class CityData {
    
    @Id
    @GeneratedValue
    private Integer id;
    private String userEmail;
    private String city;
    private String state;
    private String country;
    private String aqiUS;
    private String healthStatus;

    public CityData(CityDataDto cityDataDto) {
        this.id = cityDataDto.getId();
        this.userEmail = cityDataDto.getUserEmail();
        this.city = cityDataDto.getCity();
        this.state = cityDataDto.getState();
        this.country = cityDataDto.getCountry();
        this.aqiUS = cityDataDto.getAqiUS();
        this.healthStatus = cityDataDto.getHealthStatus();
    }
}
