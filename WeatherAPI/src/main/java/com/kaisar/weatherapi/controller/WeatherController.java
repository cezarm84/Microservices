package com.kaisar.weatherapi.controller;

import com.kaisar.weatherapi.model.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class WeatherController {
    @Value("${API_KEY}")
    private String API_KEY ;

    @GetMapping("/getWeather/lat{lat}&lon{lon}")
    public Response getWeather(@PathVariable("lat") double lat, @PathVariable("lon") double lon) {
        ResponseEntity<Response> response = new RestTemplate().getForEntity(
                "https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+ lon+"&appid="+ API_KEY,
                Response.class);
        return response.getBody();
    }
}
