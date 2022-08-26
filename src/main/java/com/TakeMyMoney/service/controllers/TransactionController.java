package com.TakeMyMoney.service.controllers;

import com.TakeMyMoney.service.controllers.requests.TransactionRequest;
import com.TakeMyMoney.service.entities.User;
import com.TakeMyMoney.service.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    TransactionService transactionService = new TransactionService();

    @PostMapping
    public ResponseEntity<User> makeTransaction(@RequestBody TransactionRequest transactionRequest) {
        return ResponseEntity.ok(transactionService.makeTransaction(transactionRequest));
    }
}
