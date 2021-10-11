package com.TakeMyMoney.service.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.security.SecureRandom;

@Data
@Builder
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private SecureRandom transactionID;

    @ManyToMany
    private String encryptedDestinationAddress;

    private BigDecimal amount;
}
