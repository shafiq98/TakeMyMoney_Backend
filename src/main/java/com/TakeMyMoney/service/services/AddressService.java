package com.TakeMyMoney.service.services;

import com.TakeMyMoney.service.entities.Address;
import com.TakeMyMoney.service.entities.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AddressService {

    public Address UserToAddress(User user){
        Address address = Address.builder()
                .id(user.getId())
                .timestamp(LocalDateTime.now())
                .pin(new SecureRandom())
                .build();

        return address;
    }

    public static boolean checkAddressValidity(Address address){
        // TODO actually test validity of pin in pinRepository
        return true;
    }
}
