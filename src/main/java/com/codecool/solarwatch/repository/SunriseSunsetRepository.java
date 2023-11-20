package com.codecool.solarwatch.repository;

import com.codecool.solarwatch.model.SunriseSunset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;


public interface SunriseSunsetRepository extends JpaRepository<SunriseSunset, Integer> {
    Optional<SunriseSunset> findByCityNameAndDate(String cityName, LocalDate date);
}
