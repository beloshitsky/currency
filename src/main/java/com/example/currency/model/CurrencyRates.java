package com.example.currency.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author Stanislav Beloshitsky
 * currency
 * 01.06.2022
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRates {
    private String disclaimer;
    private String license;
    private Integer timestamp;
    private String base;
    private Map<String, Double> rates;
}
