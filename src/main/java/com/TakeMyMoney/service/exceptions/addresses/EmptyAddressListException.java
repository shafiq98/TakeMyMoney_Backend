package com.TakeMyMoney.service.exceptions.addresses;

import com.TakeMyMoney.service.exceptions.BusinessExceptions;

public class EmptyAddressListException extends BusinessExceptions {
    public EmptyAddressListException(String errorMessage) {super(errorMessage);}
}
