package com.travelmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelmanagement.model.Activity;
import com.travelmanagement.model.Destination;
import com.travelmanagement.model.Passenger;
import com.travelmanagement.model.TravelPackage;
import com.travelmanagement.repository.ActivityRepository;
import com.travelmanagement.repository.PassengerRepository;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired private PassengerRepository passengerRepository;

    public List<Activity> getAllActivities(){
        return activityRepository.findAll();
    }

    public void saveActivity(Activity activity){
        activityRepository.save(activity);
    }

    public List<Activity> getActivitiesOfPassengerTravelPackage(int passengerId){
        Passenger passenger = passengerRepository.findById(passengerId).get();
        if(passenger != null){
            TravelPackage travelPackage = passenger.getTravelPackage();
            if(travelPackage != null){
                List<Destination> destinations = travelPackage.getDestinations();
                List<Activity> activities = new ArrayList<>();
                for (Destination destination : destinations) {
                    activities.addAll(destination.getActivities());
                }
                return activities;
            }
            
        }
        return null;
    }

    public void registerForActivity(int activityId, int passengerId) throws Exception{
        Passenger passenger = passengerRepository.findById(passengerId).get();
        Boolean sameActivity = false;
        Activity activity = activityRepository.findById(activityId).get();
        if(passenger.getActivities() != null && passenger.getActivities().contains(activity)){
            
            sameActivity = true;
        }
        
        int numberofpassengerRegistered = activity.getNumberOfPassengerRegistered();
        int capacity = activity.getCapacity();
        if(numberofpassengerRegistered<capacity){
            if(!sameActivity){
                List<Activity> activities = passenger.getActivities();
                activities.add(activity);
                passenger.setActivities(activities);
                double passengerWalletMoney = passenger.getWalletMoney();
                double activityPrice = activity.getActivityCost();

                if(passenger.getPassengerType().equalsIgnoreCase("standard") && passengerWalletMoney > activityPrice){
                    passenger.setWalletMoney(passengerWalletMoney-activityPrice);
                    passengerRepository.save(passenger);
                    activity.setNumberOfPassengerRegistered(numberofpassengerRegistered+1);
                    activityRepository.save(activity);
                }

                else if(passenger.getPassengerType().equalsIgnoreCase("gold") && passengerWalletMoney > 0.1 * activityPrice){
                    activityPrice = activityPrice - (0.1 * activityPrice);
                    passenger.setWalletMoney(passengerWalletMoney-activityPrice);
                    passengerRepository.save(passenger);
                    activity.setNumberOfPassengerRegistered(numberofpassengerRegistered+1);
                    activityRepository.save(activity);
                }

                else if(passenger.getPassengerType().equalsIgnoreCase("premium")){
                    activityPrice = 0 * activityPrice;
                    passenger.setWalletMoney(passengerWalletMoney-activityPrice);
                    passengerRepository.save(passenger);
                    activity.setNumberOfPassengerRegistered(numberofpassengerRegistered+1);
                    activityRepository.save(activity);
                }

                else{
                    throw new Exception("You Don't have Enough Money in your Wallet");
                }
            }else{
                throw new Exception("You have already booked for this activity!");
            }
        }else{
            throw new Exception("Seat is full, No more Registrations!");
        }
    }
}
