package com.codecool.solarwatch.sunriseSunset.payload;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ChangeSSDateRequest {
    private String cityName;
    private LocalDate oldDate;
    private LocalDate newDate;
}
