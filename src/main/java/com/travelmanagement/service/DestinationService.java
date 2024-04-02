package com.travelmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelmanagement.model.Destination;
import com.travelmanagement.repository.DestinationRepository;

@Service
public class DestinationService {
    @Autowired
    private DestinationRepository destinationRepository;

    public List<Destination> getAllDestinations(){
        return destinationRepository.findAll();
    }
    public void saveDestination(Destination destination){
        destinationRepository.save(destination);

    }
}
