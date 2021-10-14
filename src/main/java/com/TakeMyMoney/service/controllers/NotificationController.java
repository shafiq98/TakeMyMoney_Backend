package com.TakeMyMoney.service.controllers;

import com.TakeMyMoney.service.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    @Autowired
    private MessageService messageService;

//    @PostMapping("/subscribe")
//    public ResponseEntity<String> subscribe(@RequestBody SubscriptionRequest subscriptionRequest) {
//        messageService.subscribe(new Subscription(subscriptionRequest.endpoint,
//                new Subscription.Keys(subscriptionRequest.key, subscriptionRequest.auth)));
//        return ResponseEntity.ok(format("%s has subscribed to notifications", UserContext.getUsername()));
//    }

//    @PostMapping("/unsubscribe")
//    public ResponseEntity<String> unsubscribe() {
//        messageService.unsubscribe();
//        return ResponseEntity.ok(format("%s has unsubscribed to notifications", UserContext.getUsername()));
//    }

    @GetMapping
    public ResponseEntity<SseEmitter> emitter() {
        return ResponseEntity.ok(messageService.subscribe());
    }

//    @GetMapping
//    public ResponseEntity<String> getPublicKey() {
//        return ResponseEntity.ok(messageService.getPublicKey());
//    }
}
