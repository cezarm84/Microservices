package com.kaisar.weatherapi.controller;

import com.kaisar.weatherapi.model.ResponseWeather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {
    @Value("${API_KEY}")
    private String API_KEY ;

    @GetMapping("/getWeather/lat={lat}&lon={lon}")
    public ResponseWeather getWeather(@PathVariable("lat") double lat, @PathVariable("lon") double lon) {
        ResponseEntity<ResponseWeather> response = new RestTemplate().getForEntity(
                "https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+ lon+"&appid="+ API_KEY,
                ResponseWeather.class);
        return response.getBody();
    }
}
