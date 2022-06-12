package com.pollution.watchlistservice.dto;

import com.pollution.watchlistservice.domain.Pollution;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddToWatchlistDto {

    private long id;

    private long userId;
    private String city;
    private String state;
    private String country;
    private Pollution pollution;
}
