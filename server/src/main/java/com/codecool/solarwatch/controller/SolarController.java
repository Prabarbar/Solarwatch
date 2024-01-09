package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.city.model.City;
import com.codecool.solarwatch.sunriseSunset.model.SunriseSunset;
import com.codecool.solarwatch.city.service.CityService;
import com.codecool.solarwatch.sunriseSunset.payload.SSRequest;
import com.codecool.solarwatch.sunriseSunset.payload.ChangeSSDateRequest;
import com.codecool.solarwatch.sunriseSunset.service.SunriseSunsetService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class SolarController {

    private final SunriseSunsetService sunriseSunsetService;
    private final CityService cityService;


    public SolarController(SunriseSunsetService sunriseSunsetService, CityService cityService){
        this.sunriseSunsetService = sunriseSunsetService;
        this.cityService = cityService;
    }

//    @GetMapping("/sun")
//    @PreAuthorize("hasRole('ADMIN')")
//    public SunriseSunset getSunriseSunset(@RequestParam("city") String cityName, @RequestParam LocalDate date) {
//        City city = cityService.getCityFromDb(cityName);
//        if (city == null){
//            city = cityService.getCityFromApi(cityName);
//            cityService.addCity(city.getName(), city.getLat(), city.getLon(), city.getState(), city.getCountry());
//
//            // trzeba ponownie z db, bo wyskoczy błąd:
//            // object references an unsaved transient instance - save the transient instance before flushing
//            city = cityService.getCityFromDb(cityName);
//        }
//        SunriseSunset sunriseSunset = sunriseSunsetService.getSunriseSunsetFromDb(cityName, date);
//        if( sunriseSunset == null){
//            sunriseSunset = sunriseSunsetService.getSunriseSunsetFromApi(city, date);
//            sunriseSunsetService.addSunriseSunset(city, date, sunriseSunset.getSunrise(), sunriseSunset.getSunset());
//        }
//        return sunriseSunset;
//    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('USER')")
    @CrossOrigin(origins = "http://localhost:3000")
    public SunriseSunset getSunriseSunset(@RequestParam("city") String cityName, @RequestParam LocalDate date){
        return sunriseSunsetService.getSunriseSunsetFromDb(cityName, date);
    }

    @PostMapping("/add")
    public void addSunriseSunset(@RequestBody SSRequest request){

        City city = cityService.getCityFromDb(request.getCityName());
        if(city == null){
            city = cityService.getCityFromApi(request.getCityName());
            cityService.addCity(city.getName(), city.getLat(), city.getLon(), city.getState(), city.getCountry());
            city = cityService.getCityFromDb(request.getCityName());
        }

        SunriseSunset sunriseSunset = sunriseSunsetService.getSunriseSunsetFromApi(city, request.getDate());
        sunriseSunsetService.addSunriseSunset(city, request.getDate(), sunriseSunset.getSunrise(), sunriseSunset.getSunset());
    }

    @PutMapping("/change")
    public void changeSSDate(@RequestBody ChangeSSDateRequest request){
        sunriseSunsetService.deleteSunriseSunset(request.getCityName(), request.getOldDate());
        City city = cityService.getCityFromDb(request.getCityName());
        SunriseSunset sunriseSunset = sunriseSunsetService.getSunriseSunsetFromApi(city, request.getNewDate());
        sunriseSunsetService.addSunriseSunset(city, request.getNewDate(), sunriseSunset.getSunrise(), sunriseSunset.getSunset());
    }

    @DeleteMapping("/delete")
    public void deleteSS(@RequestBody SSRequest request){
        sunriseSunsetService.deleteSunriseSunset(request.getCityName(), request.getDate());
    }


}
