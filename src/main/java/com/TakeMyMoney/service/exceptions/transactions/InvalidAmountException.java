package com.TakeMyMoney.service.exceptions.transactions;

import com.TakeMyMoney.service.exceptions.BusinessExceptions;

public class InvalidAmountException extends BusinessExceptions {
    public InvalidAmountException(String errorMessage){super(errorMessage);}
}
