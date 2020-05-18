package com.partiravec.destinationservice.dao;

import com.partiravec.destinationservice.models.Destination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationDao extends CrudRepository<Destination, Integer> {

//    List<Destination> findByCountryId(int countryId);

    Page<Destination> findAll(Pageable pageable);

    List<Destination> findByCountryCode(String countryCode);

}
