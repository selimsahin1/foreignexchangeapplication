package com.selimsahin.foreignexchangeapplication.controller;

import com.selimsahin.foreignexchangeapplication.response.ExchangeRateResponse;
import com.selimsahin.foreignexchangeapplication.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ExchangeRateController {

    @Autowired
    ExchangeRateService exchangeRateService;

    @GetMapping("/exchange-rate")
    public ExchangeRateResponse getExhangeRate(@RequestParam String source, @RequestParam String target) {
        return ExchangeRateResponse.builder().rate(exchangeRateService.getExhangeRate(source, target)).build();
    }

}
