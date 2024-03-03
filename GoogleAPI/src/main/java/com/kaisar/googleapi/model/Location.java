package com.kaisar.googleapi.model;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Location {

    private double lat;
    private double lng;

    public Location(double lat,double lng){
        this.lat =lat;
        this.lng =lng;
    }

}
