package com.TakeMyMoney.service.controllers;

import com.TakeMyMoney.service.entities.Address;
import com.TakeMyMoney.service.entities.User;
import com.TakeMyMoney.service.services.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController("/api/v1/addresses")
public class AddressController {

    AddressService addressService = new AddressService();

    @GetMapping()
    public ResponseEntity<String> getMyAddress(UUID uuid){
        return ResponseEntity.ok(addressService.UserToAddress(uuid).toString());
    }
}
