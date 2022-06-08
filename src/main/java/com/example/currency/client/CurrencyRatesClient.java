package com.example.currency.client;

import com.example.currency.model.CurrencyRates;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author Stanislav Beloshitsky
 * currency
 * 01.06.2022
 **/

@FeignClient(name = "exchange", url = "${openexchangerates.url}")
public interface CurrencyRatesClient {
    @GetMapping(value = "/historical/{date}.json",
            params = "{app_id}")
    CurrencyRates getRates(@PathVariable String date, @RequestParam("app_id") String appId);
}
