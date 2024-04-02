package com.travelmanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.travelmanagement.model.Passenger;
import com.travelmanagement.repository.PassengerRepository;

public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private PassengerRepository passengerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Passenger passenger = passengerRepository.findByPassengerMobile(username);
       if(passenger == null){
            throw new UsernameNotFoundException("User not found");
       }
       CustomUserDetails customUserDetails = new CustomUserDetails(passenger);
       return customUserDetails;
       
    }
    
}
