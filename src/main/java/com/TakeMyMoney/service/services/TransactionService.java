package com.TakeMyMoney.service.services;

import com.TakeMyMoney.service.entities.Address;
import com.TakeMyMoney.service.entities.Transaction;
import com.TakeMyMoney.service.entities.User;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    UserService userService = new UserService();

    // TODO add exceptions to be thrown
    public User makeTransaction(Transaction transaction){

        // decrypt encrypted address string
        Address decryptedAddress = CryptoService.decrypt(transaction.getEncryptedDestinationAddress());

        // Check validity of destination address
        if (!AddressService.checkAddressValidity(decryptedAddress)){
            // TODO throw exception here
            return null;
        }
        // make deduction first
        User payer = transaction.getPayer();
        boolean deductionResult = userService.makeDeduction(payer, transaction.getAmount());
        if (!deductionResult){
            return null;
        }

        // make deposit to destination
        User destinationUser = userService.getUserByAddress(decryptedAddress);
        boolean depositResult = false;
        if (destinationUser != null){
            depositResult = userService.makeDeposit(destinationUser, transaction.getAmount());
        }

        if (depositResult){
            return userService.getUser(destinationUser.getId());
        }
        return null;
    }

}
