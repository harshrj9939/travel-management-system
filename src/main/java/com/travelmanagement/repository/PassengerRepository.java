package com.travelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelmanagement.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer>{
    public Passenger findByPassengerMobile(String mobile);
}
