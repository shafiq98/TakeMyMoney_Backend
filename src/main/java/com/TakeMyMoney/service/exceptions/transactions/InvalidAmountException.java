package com.TakeMyMoney.service.exceptions.transactions;

import com.TakeMyMoney.service.exceptions.BusinessExceptions;
import org.springframework.http.HttpStatus;

public class InvalidAmountException extends BusinessExceptions {
    public InvalidAmountException(String errorMessage){super(errorMessage, HttpStatus.BAD_REQUEST);}
}
