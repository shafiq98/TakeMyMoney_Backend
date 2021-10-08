package com.TakeMyMoney.service.services;

import com.TakeMyMoney.service.entities.Address;
import com.TakeMyMoney.service.entities.User;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@EnableScheduling
public class AddressService {

    private List<SecureRandom> pinList = new ArrayList<SecureRandom>();


    // TODO check whether iPhone will send an entire User object or just the userID?
    // If only userID, then how is the application supposed to retrieve that id? does the user have to input it during login or smth?
    public Address UserToAddress(User user){

        SecureRandom userPin = new SecureRandom();
        LocalDateTime now = LocalDateTime.now();

        pinList.add(userPin);


        Address address = Address.builder()
                .id(user.getId())
                .timestamp(now)
                .pin(userPin)
                .build();

        return address;
    }

    public Address UserToAddress(UUID userID){
        UserService userService = new UserService();

        User user = userService.getUser(userID);
        SecureRandom userPin = new SecureRandom();
        LocalDateTime now = LocalDateTime.now();

        pinList.add(userPin);


        Address address = Address.builder()
                .id(user.getId())
                .timestamp(now)
                .pin(userPin)
                .build();

        return address;
    }

    // TODO check if this scheduler removes items every 5 minutes after a pin is inserted
    @Scheduled(fixedRate = 5000)
    public void removePin(SecureRandom expiredPin){
        System.out.println(Thread.currentThread().getName()+" The removal of userPin : " + expiredPin.toString() + " executed at "+ LocalDateTime.now());
        try {
            pinList.remove(expiredPin);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static boolean checkAddressValidity(Address address){
        // TODO actually test validity of pin in pinRepository
        return true;
    }
}
