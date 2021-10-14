package com.TakeMyMoney.service.exceptions;

import org.springframework.http.HttpStatus;

public class JwtTokenException extends BusinessExceptions{
    public JwtTokenException(String message) { super(message, HttpStatus.UNAUTHORIZED); }
}