package com.travelmanagement.model;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
@Entity
public class TravelPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int packageId;
    private String packageName;
    private int passengerCapacity;
    private int numberOfPassenger;
    @ManyToMany
    private List<Destination> destinations;
    @OneToMany(mappedBy = "travelPackage")
    private List<Passenger> passengers;
    public int getPackageId() {
        return packageId;
    }
    public int getNumberOfPassenger() {
        return numberOfPassenger;
    }
    public void setNumberOfPassenger(int numberOfPassenger) {
        this.numberOfPassenger = numberOfPassenger;
    }
    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }
    public String getPackageName() {
        return packageName;
    }
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    public int getPassengerCapacity() {
        return passengerCapacity;
    }
    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }
    public List<Destination> getDestinations() {
        return destinations;
    }
    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }
    public List<Passenger> getPassengers() {
        return passengers;
    }
    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }


    
}
