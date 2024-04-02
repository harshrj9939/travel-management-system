package com.travelmanagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.travelmanagement.model.Activity;
import com.travelmanagement.model.Passenger;
import com.travelmanagement.service.ActivityService;
import com.travelmanagement.service.PassengerService;
import com.travelmanagement.service.TravelPackageService;

@Controller
@RequestMapping("/passenger")
public class PassengerController {
    @Autowired private PassengerService passengerService;
    @Autowired private TravelPackageService travelPackageService;
    @Autowired private ActivityService activityService;
    @GetMapping("/profile")
    public String getPassengerProfile(Model model){
        Passenger passenger = passengerService.getAllPassengers().get(0);
        model.addAttribute("passenger", passenger);

        return "passenger/passengerprofile";
    }
    @GetMapping("/travelpackage")
    public String getTravelPackagePage(Model model){
        model.addAttribute("travelPackages", travelPackageService.getAllTravelPackages());
        return "passenger/travelpackage";
    }
    @GetMapping("/activity")
    public String getActivityPage(Model model){
        List<Activity> activities = activityService.getActivitiesOfPassengerTravelPackage(2);

        model.addAttribute("activities", activities);
        return "passenger/activity";
    }
    @PostMapping("/booktravelpackage")
    @ResponseBody
    public Map<String, String> bookTravelPackage(@RequestBody Map<String, String> travelPackage) {
        // Process the packageId here (e.g., make a database update)
        //assume user with id 1 is logged in

        travelPackageService.bookTravelPackage(Integer.parseInt(travelPackage.get("packageId")), 2);
        Map<String, String> data = new HashMap<>();
        data.put("message", "Travel Package Booked for package: " + travelPackage);
        return data;
    }
    @PostMapping("/bookactivity")
    @ResponseBody
    public Map<String, String> bookActivity(@RequestBody Map<String, String> activity) {
        // Process the packageId here (e.g., make a database update)
        //assume user with id 1 is logged in
        Map<String, String> data = new HashMap<>();
        try{
            activityService.registerForActivity(Integer.parseInt(activity.get("activityId")), 2);
        }
        catch (Exception e){

            data.put("message", e.getMessage());
            return data;
        }
        
        data.put("message", "Successfully Registered for the activity!");
        return data;
    }
}
