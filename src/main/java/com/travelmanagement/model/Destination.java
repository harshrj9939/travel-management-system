package com.travelmanagement.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;
@Entity
public class Destination {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int destinationId;
    private String destinationName;
    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<Activity> activities;
    @ManyToMany(mappedBy = "destinations")
    private List<TravelPackage> travelPackage;

    public int getDestinationId() {
        return destinationId;
    }
    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }
    public String getDestinationName() {
        return destinationName;
    }
    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }
    public List<Activity> getActivities() {
        return activities;
    }
    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
    public List<TravelPackage> getTravelPackage() {
        return travelPackage;
    }
    public void setTravelPackage(List<TravelPackage> travelPackage) {
        this.travelPackage = travelPackage;
    }
    

    

}
