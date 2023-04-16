package com.tuum.test.viewmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionInputVM {
    private int accountId;
    private int amount;
    private String currency;
    private String direction;
    private String description;
}

