package com.codecool.solarwatch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class City {
    @Id
    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private String state;
    private String country;

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }
}
