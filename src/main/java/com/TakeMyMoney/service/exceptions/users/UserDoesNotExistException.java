package com.TakeMyMoney.service.exceptions.users;

import com.TakeMyMoney.service.exceptions.BusinessExceptions;

public class UserDoesNotExistException extends BusinessExceptions {
    public UserDoesNotExistException(String errorMessage){super(errorMessage);}
}
