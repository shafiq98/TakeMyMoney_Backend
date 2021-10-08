package com.TakeMyMoney.service.services;

import com.TakeMyMoney.service.entities.Address;
import com.TakeMyMoney.service.entities.Transaction;
import com.TakeMyMoney.service.entities.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private List<User> users = new ArrayList<User>();

    public User getUserByAddress(Address address){
        UUID userID = address.getId();
        User tempUser;
        for (int i = 0; i <= users.size(); i++){
            if (users.get(i).getId() == userID){
                tempUser = users.get(i);
                return tempUser;
            }
        }
        return null;
    }

    public User getUser(UUID userID){
        User tempUser;
        for (int i = 0; i <= users.size(); i++){
            if (users.get(i).getId() == userID){
                tempUser = users.get(i);
                return tempUser;
            }
        }
        return null;
    }


    public User createUser(){
        User tempUser = User.builder()
                .name("Shafiq")
                .balance(new BigDecimal(1500))
                .build();

        users.add(tempUser);
        return tempUser;
    }

    public User createUser(User user){
        users.add(user);
        return user;
    }

    public boolean makeDeduction(User user, BigDecimal amount){
        // 1. Verify amount < balance
        // 2. Reduce user balance
        // 3. Return true or false based on whether the change registered
        return true;
    }

    public boolean makeDeposit(User user, BigDecimal amount){
        // 1. Verify user exists
        // 2. Verify amount is > 0
        // 3. Update balance
        // 4. Return boolean based on outcome
        return true;
    }


}
