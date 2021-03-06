package com.TakeMyMoney.service.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class SchedulerService {

    @Autowired
    private AddressService addressService;

    //Use cron instead https://crontab.guru/every-5-minutes
    @Scheduled(cron = "*/600 * * * * *")
    public void pinScheduler() {
        LocalDateTime now = LocalDateTime.now();
        log.info("[Scheduler] Running at: " + now.toString());
        addressService.removePin(now);
    }

}
