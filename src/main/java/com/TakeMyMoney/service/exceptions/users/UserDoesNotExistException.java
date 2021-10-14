package com.TakeMyMoney.service.exceptions.users;

import com.TakeMyMoney.service.exceptions.BusinessExceptions;
import org.springframework.http.HttpStatus;

public class UserDoesNotExistException extends BusinessExceptions {
    public UserDoesNotExistException(String errorMessage){super(errorMessage, HttpStatus.NOT_FOUND);}
}
