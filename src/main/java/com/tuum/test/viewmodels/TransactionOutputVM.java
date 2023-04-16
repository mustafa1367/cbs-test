package com.tuum.test.viewmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionOutputVM {
    private int id;
    private int accountId;
    private int amount;
    private String currency;
    private String direction;
    private String description;
    private int balanceAfterTransaction;
}
