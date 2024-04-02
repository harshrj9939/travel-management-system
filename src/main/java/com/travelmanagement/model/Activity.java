package com.travelmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.List;

@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int activityId;
    private String activityName;
    private String activityDescription;
    private double activityCost;
    private int numberOfPassengerRegistered;
    private int capacity;
    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;
    @ManyToMany(mappedBy = "activities")
    private List<Passenger> passengers;

    public List<Passenger> getPassengers() {
        return passengers;
    }
    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
    public int getActivityId() {
        return activityId;
    }
    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }
    public String getActivityName() {
        return activityName;
    }
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
    public String getActivityDescription() {
        return activityDescription;
    }
    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }
    public double getActivityCost() {
        return activityCost;
    }
    public void setActivityCost(double activityCost) {
        this.activityCost = activityCost;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public Destination getDestination() {
        return destination;
    }
    public void setDestination(Destination destination) {
        this.destination = destination;
    }
    public int getNumberOfPassengerRegistered() {
        return numberOfPassengerRegistered;
    }
    public void setNumberOfPassengerRegistered(int numberOfPassengerRegistered) {
        this.numberOfPassengerRegistered = numberOfPassengerRegistered;
    }

    

}
