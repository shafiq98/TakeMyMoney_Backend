package com.TakeMyMoney.service.services;

import com.TakeMyMoney.service.controllers.authentication.UserContext;
import com.TakeMyMoney.service.controllers.requests.TransactionRequest;
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
    public User makeTransaction(TransactionRequest transactionRequest){

        Address address = addressService.getAddress(transactionRequest.getToken());
        User receiver = userService.getUser(address.getId());
        User sender = userService.getUser(UserContext.getUser().getId());

        sender.getBalance().add(transactionRequest.getValue());
        receiver.getBalance().add(transactionRequest.getValue().negate());

        return sender;

//        // decrypt encrypted address string
//        Address decryptedAddress = CryptoService.decrypt(transaction.getEncryptedDestinationAddress());
//
//        // Check validity of destination address
//        if (!AddressService.checkAddressValidity(decryptedAddress)){
//            // TODO throw exception here
//            return null;
//        }
//        // make deduction first
//        User payer = transaction.getPayer();
//        boolean deductionResult = userService.makeDeduction(payer, transaction.getAmount());
//        if (!deductionResult){
//            return null;
//        }
//
//        // make deposit to destination
//        User destinationUser = userService.getUserByAddress(decryptedAddress);
//        boolean depositResult = false;
//        if (destinationUser != null){
//            depositResult = userService.makeDeposit(destinationUser, transaction.getAmount());
//        }
//
//        if (depositResult){
//            return userService.getUser(destinationUser.getId());
//        }
//        return null;
    }

}
