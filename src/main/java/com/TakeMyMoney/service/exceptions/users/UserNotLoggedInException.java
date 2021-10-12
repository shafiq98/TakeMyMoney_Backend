package com.TakeMyMoney.service.exceptions.users;

import com.TakeMyMoney.service.exceptions.BusinessExceptions;

public class UserNotLoggedInException extends BusinessExceptions {
    public UserNotLoggedInException(){super("You have not logged in as a user, or your login has expired. Please login again.");}
}
