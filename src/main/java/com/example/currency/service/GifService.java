package com.example.currency.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * @author Stanislav Beloshitsky
 * currency
 * 05.06.2022
 **/

public interface GifService {
    ResponseEntity<Map> getGif(String tag);
}
