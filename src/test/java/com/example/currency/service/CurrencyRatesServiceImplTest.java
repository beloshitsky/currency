package com.example.currency.service;

import com.example.currency.client.CurrencyRatesClient;
import com.example.currency.model.CurrencyRates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

/**
 * @author Stanislav Beloshitsky
 * currency
 * 07.06.2022
 **/

@SpringBootTest
class CurrencyRatesServiceImplTest {
    @MockBean
    private CurrencyRatesClient currencyRatesClient;

    private CurrencyRates todayTestRates;
    private CurrencyRates yesterdayTestRates;

    @BeforeEach
    void setUp() {
        int timestamp = 1654549200;
        todayTestRates = new CurrencyRates();
        todayTestRates.setTimestamp(timestamp);
        todayTestRates.setBase("BASE");

        Map<String, Double> todayRates = new HashMap<>();
        todayRates.put("CUR1", 1.0);
        todayRates.put("CUR2", 1.5);
        todayRates.put("CUR3", 0.5);
        todayRates.put("BASE", 1.0);
        todayTestRates.setRates(todayRates);

        timestamp = 1654462800;
        yesterdayTestRates = new CurrencyRates();
        yesterdayTestRates.setTimestamp(timestamp);
        yesterdayTestRates.setBase("BASE");

        Map<String, Double> yesterdayRates = new HashMap<>();
        yesterdayRates.put("CUR1", 1.5);
        yesterdayRates.put("CUR2", 1.0);
        yesterdayRates.put("CUR3", 0.5);
        yesterdayRates.put("BASE", 1.0);
        yesterdayTestRates.setRates(yesterdayRates);
    }

    @Test
    void checkRates() {
        Mockito.when(currencyRatesClient.getRates(eq("2022-07-07"), anyString()))
                .thenReturn(todayTestRates);
        Mockito.when(currencyRatesClient.getRates(eq("2022-07-06"), anyString()))
                .thenReturn(yesterdayTestRates);

        assertTrue(todayTestRates.getRates().get("CUR2") > yesterdayTestRates.getRates().get("CUR2"));
        assertFalse(todayTestRates.getRates().get("CUR1") > yesterdayTestRates.getRates().get("CUR1"));
    }
}
