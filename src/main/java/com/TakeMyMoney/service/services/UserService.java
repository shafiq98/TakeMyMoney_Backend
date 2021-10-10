package com.TakeMyMoney.service.services;

import com.TakeMyMoney.service.entities.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

    public User getUser(UUID userId) {
        Optional<User> optionalUser = users.stream().filter(user -> user.getId().equals(userId)).findFirst();
        return optionalUser.orElse(null);
    }

    public User getUser(String name) {
        Optional<User> optionalUser = users.stream().filter(user -> user.getName().toLowerCase().equals(name.toLowerCase())).findFirst();
        return optionalUser.orElse(null);
    }

    public User createUser(User user) {
        users.add(user);
        return user;
    }

    public boolean makeDeduction(User user, BigDecimal amount) {
        // 1. Verify amount < balance
        // 2. Reduce user balance
        // 3. Return true or false based on whether the change registered
        return true;
    }

    public boolean makeDeposit(User user, BigDecimal amount) {
        // 1. Verify user exists
        // 2. Verify amount is > 0
        // 3. Update balance
        // 4. Return boolean based on outcome
        return true;
    }


}
