package com.kaisar.uppgift.model;



import com.kaisar.googleapi.model.Legs;
import com.kaisar.weatherapi.model.Weather;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TripDetails {

    private List<Legs> googleApiData;
    private Weather[] weatherApiData;
}