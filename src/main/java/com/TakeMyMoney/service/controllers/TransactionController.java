package com.TakeMyMoney.service.controllers;

import com.TakeMyMoney.service.entities.Transaction;
import com.TakeMyMoney.service.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class TransactionController {

    TransactionService transactionService = new TransactionService();

    @PostMapping(path = "/makeTransaction")
    public ResponseEntity<Boolean> makeTransaction(@RequestBody Transaction transaction){
        return ResponseEntity.ok(transactionService.makeTransaction(transaction));
    }
}
