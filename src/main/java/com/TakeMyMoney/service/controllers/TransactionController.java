package com.TakeMyMoney.service.controllers;

import com.TakeMyMoney.service.controllers.requests.TransactionRequest;
import com.TakeMyMoney.service.entities.Address;
import com.TakeMyMoney.service.entities.Transaction;
import com.TakeMyMoney.service.entities.User;
import com.TakeMyMoney.service.services.AddressService;
import com.TakeMyMoney.service.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {


    @Autowired
    TransactionService transactionService = new TransactionService();

    @PostMapping
    public ResponseEntity<User> makeTransaction(@RequestBody TransactionRequest transactionRequest) {
        return ResponseEntity.ok(transactionService.makeTransaction(TransactionRequestToTransaction(transactionRequest)));
    }


    private Transaction TransactionRequestToTransaction(TransactionRequest transactionRequest){
        return Transaction.builder()
                .encryptedDestinationAddress(transactionRequest.getToken())
                .amount(transactionRequest.getValue())
                .build();
    }
}
