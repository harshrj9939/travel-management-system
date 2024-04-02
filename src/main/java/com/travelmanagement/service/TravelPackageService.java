package com.travelmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelmanagement.model.Passenger;
import com.travelmanagement.model.TravelPackage;
import com.travelmanagement.repository.PassengerRepository;
import com.travelmanagement.repository.TravelPackageRepository;

@Service
public class TravelPackageService {
    @Autowired private TravelPackageRepository travelPackageRepository;
    @Autowired private PassengerRepository passengerRepository;

    public List<TravelPackage> getAllTravelPackages(){
        return travelPackageRepository.findAll();
    }
    public void saveTravelPackage(TravelPackage travelPackage){
        travelPackageRepository.save(travelPackage);
    }

    public void bookTravelPackage(int travelPackageId, int passengerId){
        Passenger passenger = passengerRepository.findById(passengerId).get();
        Boolean samepackage = false;
        if(passenger.getTravelPackage() != null && travelPackageId == passenger.getTravelPackage().getPackageId()){
            samepackage = true;
        }
        TravelPackage travelPackage = travelPackageRepository.findById(travelPackageId).get();
        int numberofpassengerRegistered = travelPackage.getNumberOfPassenger();
        int capacity = travelPackage.getPassengerCapacity();
        if(numberofpassengerRegistered<capacity){
            passenger.setTravelPackage(travelPackage);
            passengerRepository.save(passenger);
            if(!samepackage){
                travelPackage.setNumberOfPassenger(numberofpassengerRegistered+1);
                travelPackageRepository.save(travelPackage);
            }
        }
    }
}
