package com.codecool.solarwatch.sunriseSunset.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public record SolarReportResults(String sunrise, String sunset) {
}
