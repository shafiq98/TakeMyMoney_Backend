package com.TakeMyMoney.service.services;

import com.TakeMyMoney.service.entities.Address;
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
                .timestamp(LocalDateTime.now())
                .pin(new SecureRandom())
                .build();
        addressList.add(address);
        return address;
    }

    public void removePin(LocalDateTime now) {
        addressList.stream().filter(address -> address.getTimestamp().isBefore(now)).forEach(address -> {
            log.info(Thread.currentThread().getName() + " The removal of userPin : " + address.getPin() + " executed at " + LocalDateTime.now());
        });
        addressList = addressList.stream().filter(address -> address.getTimestamp().isAfter(now)).collect(Collectors.toList());
    }

    public boolean checkAddressValidity(String token) {
        List<String> addresses = Arrays.stream(CryptoService.decrypt(token).split("=")).collect(Collectors.toList());
        String secureRandom = addresses.get(2);
        Optional<Address> addressOptional = addressList.stream().filter(address -> address.getPin().toString().equals(secureRandom)).findFirst();
        return addressOptional.isPresent();
    }

    public Address getAddress(String token) {
        List<String> addresses = Arrays.stream(CryptoService.decrypt(token).split("=")).collect(Collectors.toList());
        String secureRandom = addresses.get(2);
        Optional<Address> addressOptional = addressList.stream().filter(address -> address.getPin().toString().equals(secureRandom)).findFirst();
        return addressOptional.orElse(null);
    }
}
