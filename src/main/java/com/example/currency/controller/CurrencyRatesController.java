package com.example.currency.controller;

import com.example.currency.model.CurrencyRates;
import com.example.currency.service.CurrencyRatesService;
import com.example.currency.service.GifService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Stanislav Beloshitsky
 * currency
 * 02.06.2022
 **/

@RestController
@AllArgsConstructor
public class CurrencyRatesController {

    private final CurrencyRatesService currencyRatesService;

    private final GifService gifService;

    @GetMapping("/rates")
    public CurrencyRates getAllRates() {
        return currencyRatesService.getTodayRates();
    }

    @GetMapping("/gif")
    public String getGif(@RequestParam("currency") String currencyCode) {
        String tag;
        boolean checkRate = currencyRatesService.checkRates(currencyCode);

        // если код валюты передан неправильно возвращает ошибку
        if (!currencyRatesService.getTodayRates().getRates().containsKey(currencyCode.toUpperCase())) {
            return ResponseEntity.badRequest().body("Invalid currency code").toString();
        }

        if (checkRate) {
            tag = "rich";
        } else tag = "broke";

        Map<String, String> gifInfo = (Map<String, String>) gifService.getGif(tag).getBody().get("data");

        return gifInfo.get("url");
    }
}
