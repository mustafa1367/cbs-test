package com.tuum.test.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private int id;
    private int account_id;
    private int amount;
    private int currency_id;
    private String direction;
    private String description;
}
