package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.city.model.City;
import com.codecool.solarwatch.sunriseSunset.model.SunriseSunset;
import com.codecool.solarwatch.city.service.CityService;
import com.codecool.solarwatch.sunriseSunset.service.SunriseSunsetService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class SolarController {

    private final SunriseSunsetService sunriseSunsetService;
    private final CityService cityService;


    public SolarController(SunriseSunsetService sunriseSunsetService, CityService cityService){
        this.sunriseSunsetService = sunriseSunsetService;
        this.cityService = cityService;
    }

    @GetMapping("/sun")
    @PreAuthorize("hasRole('ADMIN')")
    public SunriseSunset getSunriseSunset(@RequestParam String cityName, @RequestParam LocalDate date) {
        City city = cityService.getCityFromDb(cityName);
        if (city == null){
            city = cityService.getCityFromApi(cityName);
            cityService.addCity(city.getName(), city.getLat(), city.getLon(), city.getState(), city.getCountry());

            // trzeba ponownie z db, bo wyskoczy błąd:
            // object references an unsaved transient instance - save the transient instance before flushing
            city = cityService.getCityFromDb(cityName);
        }
        SunriseSunset sunriseSunset = sunriseSunsetService.getSunriseSunsetFromDb(cityName, date);
        if( sunriseSunset == null){
            sunriseSunset = sunriseSunsetService.getSunriseSunsetFromApi(city, date);
            sunriseSunsetService.addSunriseSunset(city, date, sunriseSunset.getSunrise(), sunriseSunset.getSunset());
        }
        return sunriseSunset;
    }
}
