package com.travelmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired PasswordEncoder passwordEncoder;
    @GetMapping("/")
    public String getHomePage(){
        return "index";
    }

    @GetMapping("/contact-us")
    public String getContactUsPage(){
        return "contact";
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
        String password = request.getParameter("password");
        Passenger passenger = new Passenger();
        passenger.setPassengerMobile(passengerMobile);
        passenger.setPassengerName(passengerName);
        passenger.setPassengerType(passengerType);
        passenger.setPassword(passwordEncoder.encode(password));
        passenger.setRole("ROLE_PASSENGER");
        passengerService.savePassenger(passenger);
    
        attributes.addFlashAttribute("success", "Your record has been saved successfully");

        return "redirect:/register-passenger";
    }
}
