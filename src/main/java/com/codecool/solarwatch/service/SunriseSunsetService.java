package com.codecool.solarwatch.service;

import com.codecool.solarwatch.model.City;
import com.codecool.solarwatch.model.SolarReport;
import com.codecool.solarwatch.model.SolarReportResults;
import com.codecool.solarwatch.model.SunriseSunset;
import com.codecool.solarwatch.repository.SunriseSunsetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class SunriseSunsetService {

    private final RestTemplate restTemplate;
    private final SunriseSunsetRepository sunriseSunsetRepository;

    private static final Logger logger = LoggerFactory.getLogger(SunriseSunsetService.class);

    public SunriseSunsetService(RestTemplate restTemplate, SunriseSunsetRepository sunriseSunsetRepository){
        this.restTemplate = restTemplate;
        this.sunriseSunsetRepository = sunriseSunsetRepository;
    }

    public SunriseSunset getSunriseSunsetFromApi(City city, LocalDate date){
        double lon = city.getLon();
        double lat = city.getLat();

        String url = "https://api.sunrise-sunset.org/json?lat="+lat+"&lng="+lon+"&date="+date;
        ResponseEntity<SolarReport> responseEntity = restTemplate.getForEntity(url, SolarReport.class);
        logger.info("Response from Sunset & Sunrise API: {}", responseEntity);
        SolarReport response = responseEntity.getBody();
        String sunrise = response.results().sunrise();
        String sunset = response.results().sunset();
        return new SunriseSunset(city, date, sunrise, sunset);
    }

    public SunriseSunset getSunriseSunsetFromDb(String cityName, LocalDate date) {
        return sunriseSunsetRepository.findByCityNameAndDate(cityName, date).orElse(null);
    }

    public void addSunriseSunset(City city, LocalDate date, String sunrise, String sunset){
        sunriseSunsetRepository.save(new SunriseSunset(city, date, sunrise, sunset));
    }
}

