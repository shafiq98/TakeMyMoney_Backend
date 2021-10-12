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
import java.time.Instant;
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
                .timestamp(LocalDateTime.now().plusMinutes(10L))
                .pin(new SecureRandom())
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

//    public boolean checkAddressValidity(String token) {
//        List<String> addresses = Arrays.stream(CryptoService.decrypt(token).split("=")).collect(Collectors.toList());
//        String secureRandom = addresses.get(2);
//        Optional<Address> addressOptional = addressList.stream().filter(address -> address.getPin().toString().equals(secureRandom)).findFirst();
//        return addressOptional.isPresent();
//    }

    // TODO check and throw EmptyAddressListException
    public boolean checkAddressValidity(String token) {
        String decryptedAddress = CryptoService.decrypt(token);
        System.out.println("Raw Token : " + decryptedAddress);

        String pin = decryptedAddress.split("=")[2];
        String id = decryptedAddress.split("=")[0];

        for (int i= 0; i<addressList.size(); i++){
            Address tempAddress = addressList.get(i);
            String tempPin = tempAddress.getPin().toString();
            String tempId = tempAddress.getId().toString();

            if (Objects.equals(tempId, id) && Objects.equals(tempPin, pin)){
                return true;
            }
        }

        return false;
    }

//    public Address getAddress(String token) {
////        System.out.println("Address List: ");
////        System.out.println(addressList);
//        List<String> addresses = Arrays.stream(token.split("=")).collect(Collectors.toList());
//        String secureRandom = addresses.get(2);
//        Optional<Address> addressOptional = addressList.stream().filter(address -> address.getPin().toString().equals(secureRandom)).findFirst();
//        return addressOptional.orElse(null);
//    }

    // TODO check and throw EmptyAddressListException
    public Address getAddress(String token) {

        if (addressList.size() == 0){
            throw new EmptyAddressListException("Address List is currently empty. Ensure there have been recent requests to generate addresses.");
        }

        String decryptedAddress = CryptoService.decrypt(token);
//        String decryptedAddress = token;
        System.out.println("Decrypted Address = " + decryptedAddress);
        // todo add null check for decryptedaddress
        String pin = decryptedAddress.split("=")[2];
        String timestamp = decryptedAddress.split("=")[1];
        String id = decryptedAddress.split("=")[0];

        for (Address tempAddress : addressList) {
            System.out.println("Temporary Address : " + tempAddress.toString());
            if (Objects.equals(tempAddress.getId().toString(), id) && Objects.equals(tempAddress.getPin().toString(), pin)) {
                System.out.println("Match Found");
                return tempAddress;
            }
        }
        throw new AddressDoesNotExistException(String.format("Sorry, there is no address generated for user with ID %s.\nCheck to ensure address has not expired.", id));

        // TODO determine why this array.stream does not work
        // check if ANY address with that UserID exists
//        Optional<Address> tempAddress = addressList.stream().filter(address -> address.getId().equals(id)).findAny();
//
//
//        if (tempAddress.isEmpty()){
//            // TODO should there be a distinction between AddressDoesNotExist and AddressHasExpiredException?
//            throw new AddressDoesNotExistException(String.format("Sorry, there is no address generated for user with ID %s.\nCheck to ensure address has not expired.", id));
//        }

        // check if the exact address (with both UserID and PIN match) exists
//        Optional<Address> addressOptional = addressList.stream().filter(address -> address.toString().equals(decryptedAddress)).findFirst();
//        if (addressOptional.isEmpty()){
//            throw new AddressHasExpiredException(String.format("The address generated by %d at %d has expired", id, timestamp));
//        }
//        return addressOptional.orElse(null);
    }

    public List<Address> getAllAddress(){
        return addressList;
    }
}
