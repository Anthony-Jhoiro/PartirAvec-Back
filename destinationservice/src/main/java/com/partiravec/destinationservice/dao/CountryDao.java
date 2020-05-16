package com.partiravec.destinationservice.dao;

import com.partiravec.destinationservice.models.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryDao extends CrudRepository<Country, Integer> {

    @Query(value = "SELECT * FROM country c INNER JOIN destination d ON d.country_id = c.id GROUP BY c.id", nativeQuery = true)
    List<Country> findAllCountriesHavingDestinations();

    boolean existsByCode(String code);

    Optional<Country> findByCode(String code);

}
