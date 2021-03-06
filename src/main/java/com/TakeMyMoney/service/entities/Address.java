package com.TakeMyMoney.service.entities;

import com.TakeMyMoney.service.services.CryptoService;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Entity
public class Address {
    @Id
    private UUID id;
    private LocalDateTime timestamp;
    private SecureRandom pin;

    // TODO add additional String encryptedString to hold toString() attribute for easier checking
    private String encryptedAddress;

    // rename toString() to maybe toEncryptedString()

    @Override
    public String toString() {
        String result = String.format("%s=%s=%s", id.toString(), timestamp.toString(), pin.toString());
//        String encryptedString = CryptoService.encrypt(result);
        String encryptedString = result;
        return encryptedString;
    }

}
