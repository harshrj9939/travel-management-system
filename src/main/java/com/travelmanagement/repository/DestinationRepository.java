package com.travelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelmanagement.model.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Integer>{
    
}
