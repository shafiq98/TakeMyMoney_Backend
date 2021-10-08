package com.TakeMyMoney.service.controllers;

import com.TakeMyMoney.service.entities.Transaction;
import com.TakeMyMoney.service.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/transactions")
public class TransactionController {

    TransactionService transactionService = new TransactionService();

    @PostMapping()
    public ResponseEntity<Boolean> makeTransaction(@RequestBody Transaction transaction){
        return ResponseEntity.ok(transactionService.makeTransaction(transaction));
    }
}
