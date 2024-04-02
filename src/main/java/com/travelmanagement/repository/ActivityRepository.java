package com.travelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelmanagement.model.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Integer>{
    
}
