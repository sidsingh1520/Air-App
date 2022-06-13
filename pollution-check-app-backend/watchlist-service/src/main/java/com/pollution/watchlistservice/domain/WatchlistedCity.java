package com.pollution.watchlistservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WatchlistedCity {
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String userEmail;
    private String city;
    private String state;
    private String country;
    private Location location;
    private Pollution pollution;
}
