package com.tuum.test.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Balance {
    private int id;
    private int account_id;
    private int currency_id;
    private int available_amount;
}
