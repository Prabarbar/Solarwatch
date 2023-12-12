package com.codecool.solarwatch.sunriseSunset.payload;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SSRequest {
    private String cityName;
    private LocalDate date;
}
