package com.codecool.solarwatch.repository;

import com.codecool.solarwatch.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface CityRepository extends JpaRepository<City, Integer> {
    Optional<City> findByName(String name);
}
