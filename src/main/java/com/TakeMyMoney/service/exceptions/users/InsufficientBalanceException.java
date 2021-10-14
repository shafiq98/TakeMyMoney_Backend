package com.TakeMyMoney.service.exceptions.users;

import com.TakeMyMoney.service.exceptions.BusinessExceptions;
import org.springframework.http.HttpStatus;

public class InsufficientBalanceException extends BusinessExceptions {
    public InsufficientBalanceException(String errorMessage){super(errorMessage, HttpStatus.BAD_REQUEST);}
}
