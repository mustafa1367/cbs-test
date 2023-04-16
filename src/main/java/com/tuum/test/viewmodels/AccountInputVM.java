package com.tuum.test.viewmodels;

import com.tuum.test.models.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountInputVM {
    private int customerId;
    private String country;
    private List<String> currencies;
}
