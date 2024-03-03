package com.kaisar.googleapi.controller;

import com.kaisar.googleapi.model.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class LocationController {
    @Value("${API_KEY}")
    private String API_KEY;

    /* @GetMapping("/test")
    public String test(){
        return "test";
    }*/

    @GetMapping("/locations")
    public Response getTripDetails(@RequestParam String origin, @RequestParam String destination ) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("maps.googleapis.com")
                .path("/maps/api/directions/json")
                .queryParam("origin", origin)
                .queryParam("destination",destination)
                .queryParam("key",API_KEY)
                .build();
        ResponseEntity<Response> response =
                new RestTemplate().getForEntity(uri.toUriString(),Response.class);
        return response.getBody();
    }
}


            