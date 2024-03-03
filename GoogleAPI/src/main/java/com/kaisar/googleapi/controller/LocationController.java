package com.kaisar.googleapi.controller;

import com.kaisar.googleapi.model.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Geocoder {
    @Value("${API_KEY}")
    private String API_KEY ;
    @GetMapping("/locations")
    public Response getTripDetails(){
        ResponseEntity<Response> response = new RestTemplate().getForEntity("https://maps.googleapis.com/maps/api/directions/json?origin=Chicago&destination=LosAngeles&key=API_KEY", Response.class);
        return response.getBody();
    }
}
