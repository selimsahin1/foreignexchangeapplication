package com.selimsahin.foreignexchangeapplication.controller;

import com.selimsahin.foreignexchangeapplication.entity.Conversion;
import com.selimsahin.foreignexchangeapplication.request.ConversionHistoryCriteriaRequest;
import com.selimsahin.foreignexchangeapplication.service.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping
public class ConversionController {
    @Autowired
    ConversionService conversionService;

    @GetMapping("/exchange-conversion")
    public Conversion getConversion(
            @RequestParam BigDecimal amount,
            @RequestParam String sourceCurrency,
            @RequestParam String targetCurrency) {
        return conversionService.getConversion(amount, sourceCurrency, targetCurrency);
    }

    @PostMapping(value = "/conversion-history")
    public Page<Conversion> getList(@RequestBody ConversionHistoryCriteriaRequest request,
                                    @PageableDefault(size = 5) Pageable pageable) {
        return conversionService.getConversionList(request.getTransactionDate(),
                request.getTransactionId(),
                pageable);
    }
}
