package com.TakeMyMoney.service.entities;

import com.TakeMyMoney.service.exceptions.users.InsufficientBalanceException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@Entity
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private BigDecimal balance;

    // TODO: Are we only identifying if id is the same?
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    public void withdraw(BigDecimal amount){

        if (balance.compareTo(amount) < 0){
            throw new InsufficientBalanceException(String.format("User %s has insufficient balance", getName()));
        }
        else{
            balance = balance.subtract(amount);
        }
    }

    public void deposit(BigDecimal amount){
        balance = balance.add(amount);
    }

    // TODO implement hashcode function? not sure what its for yet
}
