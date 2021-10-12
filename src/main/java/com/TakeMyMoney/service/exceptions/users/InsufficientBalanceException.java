package com.TakeMyMoney.service.exceptions.users;

import com.TakeMyMoney.service.exceptions.BusinessExceptions;

public class InsufficientBalanceException extends BusinessExceptions {
    public InsufficientBalanceException(String errorMessage){super(errorMessage);}
}
