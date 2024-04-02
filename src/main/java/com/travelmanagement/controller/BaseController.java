package com.travelmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.travelmanagement.model.Passenger;
import com.travelmanagement.service.PassengerService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BaseController {
    @Autowired
    private PassengerService passengerService;
    @GetMapping("/")
    public String getHomePage(){
        return "index";
    }

    @GetMapping("/register-passenger")
    public String getPassengerRegisterPage(){
        return "passengerregisterpage";
    }
    @PostMapping("/register-passenger")
    public String registerPassenger(HttpServletRequest request, RedirectAttributes attributes){
        String passengerName = request.getParameter("passengerName");
        String passengerMobile = request.getParameter("passengerMobile");
        String passengerType = request.getParameter("passengerType");
        Passenger passenger = new Passenger();
        passenger.setPassengerMobile(passengerMobile);
        passenger.setPassengerName(passengerName);
        passenger.setPassengerType(passengerType);
        passengerService.savePassenger(passenger);
        attributes.addFlashAttribute("success", "Your record has been saved successfully");

        return "redirect:/register-passenger";
    }
}
