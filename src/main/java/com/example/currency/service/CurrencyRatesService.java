package com.example.currency.service;

import com.example.currency.model.CurrencyRates;

/**
 * @author Stanislav Beloshitsky
 * currency
 * 01.06.2022
 **/

public interface CurrencyRatesService {
    CurrencyRates getTodayRates();
    CurrencyRates getYesterdayRates();
    double calculateRate(CurrencyRates currencyRates, String currencyCode);
    boolean checkRates(String currencyCode);
}
