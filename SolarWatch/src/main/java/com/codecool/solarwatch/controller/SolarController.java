package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.model.SolarReportResults;
import com.codecool.solarwatch.service.SunriseSunsetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolarController {

    private final SunriseSunsetService sunriseSunsetService;

    public SolarController(SunriseSunsetService sunriseSunsetService){
        this.sunriseSunsetService = sunriseSunsetService;
    }

    @GetMapping("/sun")
    public SolarReportResults getSunriseSunset(@RequestParam String city){
        return sunriseSunsetService.getSunriseSunset(city);
    }
}
