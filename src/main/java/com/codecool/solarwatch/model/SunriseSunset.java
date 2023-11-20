package com.codecool.solarwatch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
public class SunriseSunset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sunriseSunsetId;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    private LocalDate date;
    private String sunrise;
    private String sunset;



    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public SunriseSunset(){}
    public SunriseSunset(City city, LocalDate date, String sunrise, String sunset) {
        this.city = city;
        this.date = date;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }
}
