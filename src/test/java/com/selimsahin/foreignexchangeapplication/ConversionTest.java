package com.selimsahin.foreignexchangeapplication;

import com.selimsahin.foreignexchangeapplication.service.ConversionService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@RequiredArgsConstructor
public class ConversionTest {
    @Autowired
    ConversionService conversionService;

    @Test
    void conversionTest() {
        var currencyExchange = conversionService.getConversion(BigDecimal.valueOf(1), "USD", "TRY");
        Assertions.assertNotNull(currencyExchange.getTransactionId(), "Transaction Id must exist");
    }
}
