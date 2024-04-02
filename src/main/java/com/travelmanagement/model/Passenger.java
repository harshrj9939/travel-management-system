package com.travelmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.List;
@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int passengerNumber;
    private String passengerName;
    private String passengerType;
    @Column(unique = true, nullable = false)
    private String passengerMobile;
    private double walletMoney = 0;
    private String role;
    private String password;
    @ManyToOne
    @JoinColumn(name = "travel_package_id")
    private TravelPackage travelPackage;

    @ManyToMany
    private List<Activity> activities;

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getPassengerNumber() {
        return passengerNumber;
    }
    public void setPassengerNumber(int passengerNumber) {
        this.passengerNumber = passengerNumber;
    }
    public String getPassengerName() {
        return passengerName;
    }
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }
    public String getPassengerType() {
        return passengerType;
    }
    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }
    public String getPassengerMobile() {
        return passengerMobile;
    }
    public void setPassengerMobile(String passengerMobile) {
        this.passengerMobile = passengerMobile;
    }
    public List<Activity> getActivities() {
        return activities;
    }
    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
    public TravelPackage getTravelPackage() {
        return travelPackage;
    }
    public void setTravelPackage(TravelPackage travelPackage) {
        this.travelPackage = travelPackage;
    }
    public double getWalletMoney() {
        return walletMoney;
    }
    public void setWalletMoney(double walletMoney) {
        this.walletMoney = walletMoney;
    }

}
