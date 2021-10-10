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
        userService.createUser(new User(UUID.randomUUID(), "Shafiq", BigDecimal.ZERO));
        userService.createUser(new User(UUID.randomUUID(), "Brandon", BigDecimal.ZERO));
        userService.createUser(new User(UUID.randomUUID(), "Charmaine", BigDecimal.ZERO));
        userService.createUser(new User(UUID.randomUUID(), "Johann", BigDecimal.ZERO));
    }
}
