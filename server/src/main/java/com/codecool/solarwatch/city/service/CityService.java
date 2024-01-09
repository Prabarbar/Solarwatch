package com.codecool.solarwatch.city.service;

import com.codecool.solarwatch.city.model.City;
import com.codecool.solarwatch.city.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CityService {

    private static final String API_KEY = "";
    private final RestTemplate restTemplate;
    private final CityRepository cityRepository;
    private static final Logger logger = LoggerFactory.getLogger(CityService.class);
    public CityService(RestTemplate restTemplate, CityRepository cityRepository){
        this.restTemplate = restTemplate;
        this.cityRepository = cityRepository;
    }

    public City getCityFromApi(String cityName) {

        String url = "http://api.openweathermap.org/geo/1.0/direct?q=" + cityName + "&appid=" + API_KEY;

        ResponseEntity<City[]> response = restTemplate.getForEntity(url, City[].class);

        logger.info("Response from Geocoding API: {}", response);

        City[] cityFromApi = response.getBody();

        return cityFromApi[0];
    }

    public City getCityFromDb(String cityName) {
        return cityRepository.findByName(cityName).orElse(null);
    }

    public void addCity (String cityName, double latitude, double longitude, String state, String country){
        cityRepository.save(new City(cityName, latitude, longitude, state, country));
    }
}
