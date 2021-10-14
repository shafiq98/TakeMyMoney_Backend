package com.TakeMyMoney.service.controllers;

import com.TakeMyMoney.service.controllers.authentication.UserContext;
import com.TakeMyMoney.service.controllers.responses.AddressResponse;
import com.TakeMyMoney.service.entities.Address;
import com.TakeMyMoney.service.services.AddressService;
import com.TakeMyMoney.service.services.CryptoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<AddressResponse> generateAddress() {
        return ResponseEntity.ok(new AddressResponse(CryptoService.encrypt(addressService.generateAddress(UserContext.getUser().getId()).toString()), HttpStatus.OK));
    }

    @GetMapping(path="/allAddresses")
    public ResponseEntity<List<Address>> getAllAddress() {
        return ResponseEntity.ok(addressService.getAllAddress());
    }
}
