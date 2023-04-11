package com.TakeMyMoney.service.services;

import com.TakeMyMoney.service.entities.Address;
import com.TakeMyMoney.service.entities.User;
import com.TakeMyMoney.service.exceptions.addresses.AddressDoesNotExistException;
import com.TakeMyMoney.service.exceptions.addresses.AddressHasExpiredException;
import com.TakeMyMoney.service.exceptions.addresses.EmptyAddressListException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@EnableScheduling
public class AddressService {

    @Autowired
    private UserService userService;

    private List<Address> addressList = new ArrayList<>();

    public Address generateAddress(UUID userID) {
        Address address = Address.builder()
                .id(userID)
                .build();
        addressList.add(address);
        // should the scheduled remover be placed here?
        return address;
    }

    public void removePin(LocalDateTime now) {
        addressList.stream().filter(address -> address.getTimestamp().isBefore(now)).forEach(address -> {
            log.info(Thread.currentThread().getName() + " The removal of userPin : " + address.getPin() + " executed at " + LocalDateTime.now());
        });
        addressList = addressList.stream().filter(address -> address.getTimestamp().isAfter(now)).collect(Collectors.toList());
    }

    // TODO check and throw EmptyAddressListException
    public Address getAddress(String token) {

        if (addressList.size() == 0){
            throw new EmptyAddressListException("Address List is currently empty. Ensure there have been recent requests to generate addresses.");
        }

        String decryptedAddress = token;
        String id = decryptedAddress.split("=")[0];

        for (int i = 0; i< addressList.size(); i++){
            Address tempAddress = addressList.get(i);
            System.out.println("Temporary Address : " + tempAddress.toString());
            if (Objects.equals(tempAddress.getId().toString(), id)){
                System.out.println("Match Found");
                return tempAddress;
            }
        }
        throw new AddressDoesNotExistException(String.format("Sorry, there is no address generated for user with ID %s.\nCheck to ensure address has not expired.", id));

    }

    public List<Address> getAllAddress(){
        return addressList;
    }
}
