package com.TakeMyMoney.service.services;

import com.TakeMyMoney.service.controllers.authentication.UserContext;
import com.TakeMyMoney.service.entities.Address;
import com.TakeMyMoney.service.entities.Transaction;
import com.TakeMyMoney.service.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TransactionService {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;

    // TODO add exceptions to be thrown
    public User makeTransaction(Transaction transaction){
        User sender = UserContext.getUser();
        String encryptedDestinationAddress = transaction.getEncryptedDestinationAddress();
        System.out.println(encryptedDestinationAddress);

        Address destination = addressService.getAddress(encryptedDestinationAddress);
        User receiver = userService.getUser(destination.getId());
        System.out.println(receiver.getId());

        sender.setBalance(sender.getBalance().add(transaction.getAmount().negate()));;
        receiver.setBalance(receiver.getBalance().add(transaction.getAmount()));


        return sender;
    }
}
