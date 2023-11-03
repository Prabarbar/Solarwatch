package com.codecool.solarwatch.service;

import com.codecool.solarwatch.model.SolarReport;
import com.codecool.solarwatch.model.SolarReportResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SunriseSunsetService {

    private final LatitudeLongitudeService latitudeLongitudeService;
    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(SunriseSunsetService.class);

    public SunriseSunsetService(RestTemplate restTemplate, LatitudeLongitudeService latitudeLongitudeService){
        this.restTemplate = restTemplate;
        this.latitudeLongitudeService = latitudeLongitudeService;
    }

    public SolarReportResults getSunriseSunset(String city){
        double lat = latitudeLongitudeService.getLatitudeLongitude(city).lat();
        double lon = latitudeLongitudeService.getLatitudeLongitude(city).lon();
        String url = "https://api.sunrise-sunset.org/json?lat="+lat+"&lng="+lon;

        SolarReport response = restTemplate.getForObject(url, SolarReport.class);

        logger.info("Response from Sunset & Sunrise API: {}", response);

        return new SolarReportResults(response.results().sunrise(),
                response.results().sunset());
    }
}
