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

    public List<User> getUsers(){
        return users;
    }

    public User getUser(UUID userId) {
        Optional<User> optionalUser = users.stream().filter(user -> user.getId().equals(userId)).findFirst();
        return optionalUser.orElse(null);
    }

    public String getUserName(UUID userId) {
        Optional<User> optionalUser = users.stream().filter(user -> user.getId().equals(userId)).findFirst();
        return optionalUser.get().getName();
    }

    public User getUser(String name) {
        Optional<User> optionalUser = users.stream().filter(user -> user.getName().toLowerCase().equals(name.toLowerCase())).findFirst();
        return optionalUser.orElse(null);
    }

    public User createUser(User user) {
        users.add(user);
        return user;
    }


}
