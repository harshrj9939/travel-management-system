package com.travelmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.travelmanagement.model.Activity;
import com.travelmanagement.model.Destination;
import com.travelmanagement.model.TravelPackage;
import com.travelmanagement.service.ActivityService;
import com.travelmanagement.service.DestinationService;
import com.travelmanagement.service.PassengerService;
import com.travelmanagement.service.TravelPackageService;

import jakarta.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private DestinationService destinationService;
    @Autowired private ActivityService activityService;
    @Autowired private PassengerService passengerService;
    @Autowired private TravelPackageService travelPackageService;


    @GetMapping("/destination")
    public String getDestinationPage(Model model){
        List<Destination> destinations = destinationService.getAllDestinations();
        model.addAttribute("destinations", destinations);
        return "admin/destination";
    }
    @PostMapping("/destination")
    public String addDestination(HttpServletRequest request, RedirectAttributes attributes){
        String destinationName = request.getParameter("destinationName");
        Destination destination = new Destination();
        destination.setDestinationName(destinationName);
        destinationService.saveDestination(destination);
        attributes.addFlashAttribute("success", "Destination Added Succefully!");
        return "redirect:/admin/destination";
    }
    @GetMapping("/activity")
    public String getActivityPage(Model model){
        List<Activity> activities = activityService.getAllActivities();
        List<Destination> destinations = destinationService.getAllDestinations();
        model.addAttribute("activities", activities);
        model.addAttribute("destinations", destinations);
        return "admin/activity";
    }
    @PostMapping("/activity")
    public String addActivity(HttpServletRequest request, RedirectAttributes attributes){
        String activityName = request.getParameter("activityName");
        String activityDescription = request.getParameter("activityDescription");
        String activityCost = request.getParameter("activityCost");
        String activityCapacity = request.getParameter("activityCapacity");
        String destination = request.getParameter("destination");
        Activity activity = new Activity();
        
        try{
            activity.setActivityName(activityName);
            activity.setActivityDescription(activityDescription);
            activity.setActivityCost(Double.parseDouble(activityCost));
            activity.setCapacity(Integer.parseInt(activityCapacity));
            Destination destinationObj = new Destination();
            destinationObj.setDestinationId(Integer.parseInt(destination));
            activity.setDestination(destinationObj);
            activityService.saveActivity(activity);
            attributes.addFlashAttribute("success", "Activity Added Successfully!");
        }
        catch(Exception e){
            String message = e.getMessage();
            e.printStackTrace();
            attributes.addFlashAttribute("success", message);
        }
        return "redirect:/admin/activity";
    }
    @GetMapping("/passenger")
    public String getPassengerPage(Model model){
        model.addAttribute("passengers", passengerService.getAllPassengers());
        return "admin/passenger";
    }
    @GetMapping("/travelpackage")
    public String getTravelPackagePage(Model model){
        model.addAttribute("travelPackages", travelPackageService.getAllTravelPackages());
        model.addAttribute("destinations", destinationService.getAllDestinations());
        return "admin/travelpackage";
    }
    @PostMapping("/travelpackage")
    public String addTravelPackage(HttpServletRequest request, RedirectAttributes attributes){
        String[] destinations = request.getParameterValues("travelPackageDestinations");
        String packageName = request.getParameter("travelPackageName");
        String travelPackageCapacity = request.getParameter("travelPackageCapacity");
        TravelPackage travelPackage = new TravelPackage();
        List<Destination> destinations2 = new ArrayList<>();
        for (String destination : destinations) {
            Destination destination3 = new Destination();
            destination3.setDestinationId(Integer.parseInt(destination));
            destinations2.add(destination3);
        }
        for (Destination destination : destinations2) {
            System.out.println(destination.getDestinationId());
        }
        try{
            travelPackage.setPackageName(packageName);
            travelPackage.setPassengerCapacity(Integer.parseInt(travelPackageCapacity));
            travelPackage.setDestinations(destinations2);
            travelPackageService.saveTravelPackage(travelPackage);
            attributes.addFlashAttribute("success", "Travel Package Added Successfully!");
        }
        catch(Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("success", e.getMessage());
        }
        return "redirect:/admin/travelpackage";
    }
}
