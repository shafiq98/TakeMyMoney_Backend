package com.TakeMyMoney.service.exceptions.addresses;

import com.TakeMyMoney.service.exceptions.BusinessExceptions;

public class AddressDoesNotExistException extends BusinessExceptions {
    public AddressDoesNotExistException(String errorMessage){super(errorMessage);}
}
