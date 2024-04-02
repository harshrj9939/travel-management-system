package com.travelmanagement.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.travelmanagement.model.Passenger;
import com.travelmanagement.service.PassengerService;

@ControllerAdvice
public class CommonData {
    @Autowired PassengerService passengerService;
    @ModelAttribute("loggedInPassenger")
    public Passenger getLoggedInPassenger(Principal principal){
        Passenger passenger = null;
        try{
            String username = principal.getName();
            passenger = passengerService.getPassengerByMobile(username);
            return passenger;
        }catch(Exception e){
            return passenger;
        }
    }

}
