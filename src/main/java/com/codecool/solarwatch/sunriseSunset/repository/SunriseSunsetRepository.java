package com.codecool.solarwatch.sunriseSunset.repository;

import com.codecool.solarwatch.sunriseSunset.model.SunriseSunset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;


public interface SunriseSunsetRepository extends JpaRepository<SunriseSunset, Integer> {
    Optional<SunriseSunset> findByCityNameAndDate(String cityName, LocalDate date);
}
