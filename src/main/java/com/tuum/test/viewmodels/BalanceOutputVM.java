package com.tuum.test.viewmodels;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceOutputVM implements Serializable {
    private static final long serialVersionUID = 1L;
    private int availableBalance;
    private String currency;
}
