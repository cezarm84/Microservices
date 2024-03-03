// TripController.java
package com.kaisar.uppgift.controller;

import com.kaisar.googleapi.model.Legs;
import com.kaisar.googleapi.model.Location;
import com.kaisar.googleapi.model.Response;
import com.kaisar.uppgift.model.TripDetails;
import com.kaisar.weatherapi.model.ResponseWeather;
import com.kaisar.weatherapi.model.Coordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class TripController {

    private final RestTemplate restTemplate;

    @Autowired
    public TripController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/getTripDetails")
    public TripDetails getTripDetails(@RequestParam("startAddress") String startAddress,
                                      @RequestParam("endAddress") String endAddress) {
        TripDetails tripDetails = new TripDetails();

        // Call Google API
        Response googleApiResponse = restTemplate.getForObject(
                "https://maps.googleapis.com/maps/api/directions/json?origin=" + startAddress +
                        "&destination=" + endAddress + "&key=YOUR_GOOGLE_API_KEY",
                Response.class);

        if (googleApiResponse != null && googleApiResponse.getRoutes() != null && googleApiResponse.getRoutes().length > 0) {
            tripDetails.setGoogleApiData(List.of(googleApiResponse.getRoutes()[0].getLegs()));
        }

        // Call Weather API
        Coordinates coordinates = getCoordinatesFromGoogleApi(googleApiResponse);
        if (coordinates != null) {
            ResponseWeather weatherApiResponse = restTemplate.getForObject(
                    "http://weather-api-module-host/getWeather/lat=" + coordinates.getLat() +
                            "&lon=" + coordinates.getLon(),
                    ResponseWeather.class);

            if (weatherApiResponse != null) {
                tripDetails.setWeatherApiData(weatherApiResponse.getWeather());
            }
        }

        return tripDetails;
    }

    private Coordinates getCoordinatesFromGoogleApi(Response googleApiResponse) {
        // Extract coordinates from Google API response
        if (googleApiResponse != null && googleApiResponse.getRoutes() != null && googleApiResponse.getRoutes().length > 0) {
            Legs firstLeg = googleApiResponse.getRoutes()[0].getLegs()[0];
            if (firstLeg != null) {
                Location startLocation = firstLeg.getStartLocation();
                if (startLocation != null) {
                    return new Coordinates(startLocation.getLat(), startLocation.getLng());
                }
            }
        }
        return null;
    }
}
