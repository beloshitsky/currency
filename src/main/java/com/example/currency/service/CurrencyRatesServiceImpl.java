package com.example.currency.service;

import com.example.currency.client.CurrencyRatesClient;
import com.example.currency.model.CurrencyRates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Stanislav Beloshitsky
 * currency
 * 01.06.2022
 **/

@Service
public class CurrencyRatesServiceImpl implements CurrencyRatesService {
    private CurrencyRatesClient currencyRatesClient;

    @Value("${openexchangerates.app.id}")
    private String appId;

    @Value("${openexchangerates.base}")
    private String base;

    public CurrencyRatesServiceImpl(CurrencyRatesClient client) {
        this.currencyRatesClient = client;
    }

    @Override
    public CurrencyRates getTodayRates() {

        String today = LocalDate.now()
                .format(DateTimeFormatter.ISO_LOCAL_DATE);

        return currencyRatesClient.getRates(today, appId);
    }

    @Override
    public CurrencyRates getYesterdayRates() {

        String yesterday = LocalDate.now().minusDays(1)
                .format(DateTimeFormatter.ISO_LOCAL_DATE);

        return currencyRatesClient.getRates(yesterday, appId);
    }

    /* рассчитывает курс валюты (currencyCode) к базе, установленной в application.properties,
     * поскольку в openexchangerates по умолчанию база USD
     */
    @Override
    public double calculateRate(CurrencyRates currencyRates, String currencyCode) {
        try {
            double requiredRate = currencyRates.getRates().get(currencyCode.toUpperCase());
            double baseRates = currencyRates.getRates().get(base);
            return baseRates / requiredRate;
        } catch (NullPointerException e) {
            return -1;
        }
    }

    // проверка курса валюты по отношению к предыдущему дню
    @Override
    public boolean checkRates(String currencyCode) {
        double todayRate = calculateRate(getTodayRates(), currencyCode.toUpperCase());
        double yesterdayRate = calculateRate(getYesterdayRates(), currencyCode.toUpperCase());

        return todayRate > yesterdayRate;
    }
}
