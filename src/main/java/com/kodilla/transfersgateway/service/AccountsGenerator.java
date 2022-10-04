package com.kodilla.transfersgateway.service;

import com.kodilla.transfersgateway.domain.Account;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class AccountsGenerator {
    public List<Account> generateAccounts() {
        return List.of(
                new Account("1", new BigDecimal("400.00")),
                new Account("2", new BigDecimal("40.00")),
                new Account("3", new BigDecimal("560.00")),
                new Account("4", new BigDecimal("2990.90")),
                new Account("5", new BigDecimal("555.00"))
        );
    }
}
