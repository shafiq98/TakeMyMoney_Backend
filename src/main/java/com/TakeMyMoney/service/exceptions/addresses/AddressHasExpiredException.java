package com.TakeMyMoney.service.exceptions.addresses;

import com.TakeMyMoney.service.exceptions.BusinessExceptions;

public class AddressHasExpiredException extends BusinessExceptions {
    public AddressHasExpiredException(String errorMessage){super(errorMessage);}
}
