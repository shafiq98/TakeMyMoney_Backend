package com.TakeMyMoney.service.controllers.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserRequest {
    private String name;
    private BigDecimal balance;
}
