package com.tuum.test.viewmodels;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountOutputVM implements Serializable {
    private static final long serialVersionUID = 1L;
    private int accountId;
    private int customerId;
    private List<BalanceOutputVM> balances;
}

