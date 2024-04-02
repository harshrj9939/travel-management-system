package com.travelmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelmanagement.model.Passenger;
import com.travelmanagement.repository.PassengerRepository;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    public void savePassenger(Passenger passenger){
        passengerRepository.save(passenger);
    }
    public List<Passenger> getAllPassengers(){
        return passengerRepository.findAll();
    }
    public Passenger getPassengerByMobile(String mobile){
        return passengerRepository.findByPassengerMobile(mobile);
    }
}
