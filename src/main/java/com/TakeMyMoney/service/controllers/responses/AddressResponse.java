package com.TakeMyMoney.service.controllers.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
    private String addressToken;
    private HttpStatus httpStatus;
}
