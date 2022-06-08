package com.example.currency.service;

import com.example.currency.client.GifClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Stanislav Beloshitsky
 * currency
 * 05.06.2022
 **/

@Service
public class GifServiceImpl implements GifService {

    private final GifClient gifClient;

    @Value("${giphy.api.key}")
    private String apiKey;

    public GifServiceImpl(GifClient client) {
        this.gifClient = client;
    }

    @Override
    public ResponseEntity<Map> getGif(String tag) {
        return gifClient.getGif(apiKey, tag);
    }
}
