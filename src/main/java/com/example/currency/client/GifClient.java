package com.example.currency.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author Stanislav Beloshitsky
 * currency
 * 05.06.2022
 **/

@FeignClient(name = "gif", url = "${giphy.url}")
public interface GifClient {
    @GetMapping(params = {"api_key", "tag"})
    ResponseEntity<Map> getGif(@RequestParam("api_key") String apiKey,
                          @RequestParam("tag") String tag);
}
