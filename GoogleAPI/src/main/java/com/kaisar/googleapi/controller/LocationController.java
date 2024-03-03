package com.kaisar.googleapi.controller;

import com.kaisar.googleapi.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class LocationController {

    private static final Logger logger = LoggerFactory.getLogger(LocationController.class);

    private final String apiKey;
    private final RestTemplate restTemplate;

    @Autowired
    public LocationController(@Value("${API_KEY}") String apiKey, RestTemplate restTemplate) {
        this.apiKey = apiKey;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/locations")
    public Response getTripDetails() {
        try {
            ResponseEntity<Response> response = restTemplate.getForEntity(
                    "https://maps.googleapis.com/maps/api/directions/json?origin=landvetter&destination=stockholm&key=" + apiKey,
                    Response.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                logger.error("Error in API response: {}", response.getStatusCode());
                // Handle error appropriately or return a default response
                return new Response();
            }
        } catch (Exception e) {
            logger.error("Error while making API request", e);
            // Handle exception appropriately or return a default response
            return new Response();
        }
    }
}


            