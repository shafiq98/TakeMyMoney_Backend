package com.TakeMyMoney.service.exceptions;

import com.TakeMyMoney.service.controllers.responses.BusinessExceptionResponse;
import com.TakeMyMoney.service.exceptions.BusinessExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor{
    @ExceptionHandler(BusinessExceptions.class)
    @ResponseBody
    public ResponseEntity responseMyException(BusinessExceptions e) {
        return new ResponseEntity(new BusinessExceptionResponse(e.getMessage()), e.getHTTPStatus());
    }
}
