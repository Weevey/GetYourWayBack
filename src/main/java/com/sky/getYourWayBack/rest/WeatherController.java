package com.sky.getYourWayBack.rest;


import com.sky.getYourWayBack.data.entity.Weather;
import org.springframework.web.bind.annotation.*;
import com.sky.getYourWayBack.service.WeatherService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class WeatherController {
    private WeatherService service;
    public WeatherController(WeatherService service) {
        this.service = service;
    }


//    public GYWController(WeatherService service) {
//        this.service = service;
//    }

    @GetMapping("/weather/{location}")
    public Weather weatherRequest(@PathVariable String location) {

        return this.service.getWeather(location);
    }
    @GetMapping("/weather")
    public Weather weatherRequestByCityName(@RequestParam String cityName) {
        return this.service.getWeather(cityName);
    }
}
