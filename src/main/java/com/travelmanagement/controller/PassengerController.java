package com.travelmanagement.controller;

import java.security.Principal;
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

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/passenger")
public class PassengerController {
    @Autowired private PassengerService passengerService;
    @Autowired private TravelPackageService travelPackageService;
    @Autowired private ActivityService activityService;
    @GetMapping("/profile")
    public String getPassengerProfile(Model model, Principal principal){

        try{
            String username = principal.getName();
            Passenger passenger = passengerService.getPassengerByMobile(username);
            model.addAttribute("passenger", passenger);
    
            return "passenger/passengerprofile";
        }
        catch (Exception e){
            //if user is not logged in return to home page
            return "index";
        }
        
    }
    @GetMapping("/travelpackage")
    public String getTravelPackagePage(Model model){
        model.addAttribute("travelPackages", travelPackageService.getAllTravelPackages());
        return "passenger/travelpackage";
    }
    @GetMapping("/activity")
    public String getActivityPage(Model model, Principal principal){
        try{
            String username = principal.getName();
            Passenger passenger = passengerService.getPassengerByMobile(username);
            List<Activity> activities = activityService.getActivitiesOfPassengerTravelPackage(passenger.getPassengerNumber());

            model.addAttribute("activities", activities);
            return "passenger/activity";
        }
        catch(Exception e){
            //if user is not logged in return to home page
            return "index";
        }
       
    }
    @PostMapping("/booktravelpackage")
    @ResponseBody
    public Map<String, String> bookTravelPackage(@RequestBody Map<String, String> travelPackage, Principal principal) {
        // Process the packageId here (e.g., make a database update)
        //assume user with id 1 is logged in
        Map<String, String> data = new HashMap<>();
        try{
            String username = principal.getName();
            Passenger passenger = passengerService.getPassengerByMobile(username);
            travelPackageService.bookTravelPackage(Integer.parseInt(travelPackage.get("packageId")), passenger.getPassengerNumber());
            
            data.put("message", "Travel Package Booked for package: " + travelPackage);
            return data;
        }
        catch(Exception e){
            data.put("message", "Something Went wrong! ");
            return data;
        }
        
    }
    @PostMapping("/bookactivity")
    @ResponseBody
    public Map<String, String> bookActivity(@RequestBody Map<String, String> activity, Principal principal) {
        // Process the packageId here (e.g., make a database update)
        //assume user with id 1 is logged in
        Map<String, String> data = new HashMap<>();
        try{
            String username = principal.getName();
            Passenger passenger = passengerService.getPassengerByMobile(username);
            activityService.registerForActivity(Integer.parseInt(activity.get("activityId")), passenger.getPassengerNumber());
        }
        catch (Exception e){

            data.put("message", e.getMessage());
            return data;
        }
        
        data.put("message", "Successfully Registered for the activity!");
        return data;
    }
    @PostMapping("/addbalance")
    public String addBalance(HttpServletRequest request, Principal principal){
        try{
            String username = principal.getName();
            Passenger passenger = passengerService.getPassengerByMobile(username);
            double walletMoney = Double.parseDouble(request.getParameter("walletMoney"));
            double availableBalance = passenger.getWalletMoney();
            passenger.setWalletMoney(availableBalance + walletMoney);
            passengerService.savePassenger(passenger);
        }
        catch(Exception e){
            return "redirect:/passenger/profile";
        }
        return "redirect:/passenger/profile";
    }
}
