package com.selimsahin.foreignexchangeapplication.controller;

import com.selimsahin.foreignexchangeapplication.entity.Conversion;
import com.selimsahin.foreignexchangeapplication.service.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/conversion")
public class ConversionController {
    @Autowired
    ConversionService conversionService;

    @GetMapping("/get")
    public Conversion getConversion(
            @RequestParam BigDecimal amount,
            @RequestParam String targetCurrency) throws Exception {
        return conversionService.getConversion(amount, targetCurrency);
    }

    @GetMapping(value = "/getList")
    public List<Conversion> fetchResult(@RequestParam(required = false) String transactionDate,
                                        @RequestParam(required = false) Long conversionId,
                                        @RequestParam Integer pageNumber,
                                        @RequestParam Integer pageSize) {
        return conversionService.getConversionList(transactionDate, conversionId, pageNumber, pageSize);
    }
}
