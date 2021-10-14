package com.TakeMyMoney.service.exceptions.addresses;

import com.TakeMyMoney.service.exceptions.BusinessExceptions;
import org.springframework.http.HttpStatus;

public class EmptyAddressListException extends BusinessExceptions {
    public EmptyAddressListException(String errorMessage) {super(errorMessage, HttpStatus.NOT_FOUND);}
}
