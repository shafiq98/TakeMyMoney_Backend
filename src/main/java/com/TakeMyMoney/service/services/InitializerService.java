package com.TakeMyMoney.service.services;

import com.TakeMyMoney.service.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class InitializerService {

    @Autowired
    private UserService userService;

    public void initialise() {
        userService.createUser(new User(UUID.randomUUID(), "Shafiq", new BigDecimal("150.00")));
        userService.createUser(new User(UUID.randomUUID(), "Brandon", new BigDecimal("250.00")));
        userService.createUser(new User(UUID.randomUUID(), "Charmaine", new BigDecimal("120.00")));
        userService.createUser(new User(UUID.randomUUID(), "Johann", new BigDecimal("1000.00")));
    }
}
