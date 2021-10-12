package com.TakeMyMoney.service.controllers.authentication;

import com.TakeMyMoney.service.entities.User;
import com.TakeMyMoney.service.exceptions.users.UserNotLoggedInException;

import java.util.UUID;

public class UserContext {

    private static User user;

    public static void setContext(User loggedInUser) {
        user = loggedInUser;
    }

    public static void clearContext() {
        user = null;
    }

    public static User getUser() {
        return user;
    }

    public static UUID getUserId() {
        return user.getId();
    }

    public static String getUsername() {
        return user.getName();
    }
}
