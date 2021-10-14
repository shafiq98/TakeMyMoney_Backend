package com.TakeMyMoney.service.exceptions;

import org.springframework.http.HttpStatus;


public class BusinessExceptions extends RuntimeException{

    private HttpStatus httpStatus;

    public BusinessExceptions(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
    public HttpStatus getHTTPStatus(){
        return httpStatus;
    }
}
