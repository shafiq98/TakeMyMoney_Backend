package com.TakeMyMoney.service.services;

import com.TakeMyMoney.service.controllers.authentication.UserContext;
import com.TakeMyMoney.service.entities.Address;
import com.TakeMyMoney.service.entities.Transaction;
import com.TakeMyMoney.service.entities.User;
import com.TakeMyMoney.service.exceptions.transactions.InvalidAmountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


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

        BigDecimal transactionAmount = transaction.getAmount();

        if (transactionAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAmountException(String.format("Sorry, we do not accept negative transaction amounts like $%s", transactionAmount));
        }

        // TODO check for and throw InsufficientBalanceException
//        sender.setBalance(sender.getBalance().add(transactionAmount.negate()));;
//        receiver.setBalance(receiver.getBalance().add(transactionAmount));
        sender.withdraw(transactionAmount);
        receiver.deposit(transactionAmount);


        return sender;
    }
}
