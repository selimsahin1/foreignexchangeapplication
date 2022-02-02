package com.selimsahin.foreignexchangeapplication.controller;

import com.selimsahin.foreignexchangeapplication.entity.ExhangeRate;
import com.selimsahin.foreignexchangeapplication.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exhangeRate")
public class ExchangeRateController {

    @Autowired
    ExchangeRateService exchangeRateService;

    @GetMapping("/get")
    public ExhangeRate getExhangeRate(@RequestParam String symbols) throws Exception {
        return exchangeRateService.getExhangeRate(symbols);
    }

}
