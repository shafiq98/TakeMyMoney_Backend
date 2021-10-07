package com.TakeMyMoney.service.controllers;

import com.TakeMyMoney.service.entities.Address;
import com.TakeMyMoney.service.entities.User;
import com.TakeMyMoney.service.services.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class AddressController {

    AddressService addressService = new AddressService();

    @GetMapping(path="/address")
    public ResponseEntity<String> getMyAddress(User user){
        return ResponseEntity.ok(addressService.UserToAddress(user).toString());
    }
}
