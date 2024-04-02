package com.travelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelmanagement.model.TravelPackage;

public interface TravelPackageRepository extends JpaRepository<TravelPackage, Integer>{
    
}
