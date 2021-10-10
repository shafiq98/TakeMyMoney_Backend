package com.TakeMyMoney.service.controllers;

import com.TakeMyMoney.service.controllers.authentication.UserContext;
import com.TakeMyMoney.service.controllers.responses.AddressResponse;
import com.TakeMyMoney.service.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<AddressResponse> generateAddress() {
        return ResponseEntity.ok(new AddressResponse(addressService.generateAddress(UserContext.getUser().getId()).toString()));
    }
}
