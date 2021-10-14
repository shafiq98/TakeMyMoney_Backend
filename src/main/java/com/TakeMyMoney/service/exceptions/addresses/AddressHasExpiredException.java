package com.TakeMyMoney.service.exceptions.addresses;

import com.TakeMyMoney.service.exceptions.BusinessExceptions;
import org.springframework.http.HttpStatus;

public class AddressHasExpiredException extends BusinessExceptions {
    public AddressHasExpiredException(String errorMessage){super(errorMessage, HttpStatus.BAD_REQUEST);}
}
