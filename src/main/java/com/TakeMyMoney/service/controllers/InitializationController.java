package com.TakeMyMoney.service.controllers;

import com.TakeMyMoney.service.services.InitializerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/initialize")
public class InitializationController {

    @Autowired
    private InitializerService initializerService;

    @PostMapping
    public void initialize() {
        initializerService.initialise();
    }
}
