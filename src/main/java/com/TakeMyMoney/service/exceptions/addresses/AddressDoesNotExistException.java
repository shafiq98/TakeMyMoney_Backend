package com.TakeMyMoney.service.exceptions.addresses;

import com.TakeMyMoney.service.exceptions.BusinessExceptions;
import org.springframework.http.HttpStatus;

public class AddressDoesNotExistException extends BusinessExceptions {
    public AddressDoesNotExistException(String errorMessage){
        super(errorMessage, HttpStatus.NOT_FOUND);
    }
}
