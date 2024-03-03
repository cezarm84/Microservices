package com.kaisar.weatherapi.model;



import lombok.Getter;
import lombok.Setter;
@Setter
@Getter

public class Coordinates {

    private double lat;
    private double lon;

    public Coordinates(double lat, double lng) {
    }
}
