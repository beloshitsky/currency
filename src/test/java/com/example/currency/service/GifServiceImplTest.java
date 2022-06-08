package com.example.currency.service;

import com.example.currency.client.GifClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.*;

/**
 * @author Stanislav Beloshitsky
 * currency
 * 08.06.2022
 **/

@SpringBootTest
class GifServiceImplTest {
    @MockBean
    GifClient gifClient;

    @Test
    void getGif() {
        Mockito.when(gifClient.getGif(anyString(), eq("rich")))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        Mockito.when(gifClient.getGif(anyString(), eq("broke")))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));
    }
}
