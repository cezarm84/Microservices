package com.kaisar.weatherapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseWeather {
    @JsonProperty("coord")
    private Coordinates coordinates;
    private  Weather[] weather;
    private Main main;

}
