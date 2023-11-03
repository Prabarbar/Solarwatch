package com.codecool.solarwatch.service;

import com.codecool.solarwatch.model.LatitudeLongitude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LatitudeLongitudeService {

    private static final String API_KEY = "13a7b088b933337f7679ee595833f0ec";
    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(LatitudeLongitudeService.class);
    public LatitudeLongitudeService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public LatitudeLongitude getLatitudeLongitude(String city){

        String url = "http://api.openweathermap.org/geo/1.0/direct?q="+city+"&appid="+API_KEY;

        ResponseEntity<LatitudeLongitude[]> response = restTemplate.getForEntity(url, LatitudeLongitude[].class);

        logger.info("Response from Geocoding API: {}", response);

        return new LatitudeLongitude(
                response.getBody()[0].lat(),
                response.getBody()[0].lon()
        );
    }
}
