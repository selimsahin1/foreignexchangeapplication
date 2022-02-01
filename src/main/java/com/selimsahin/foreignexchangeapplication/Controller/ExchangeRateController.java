package com.selimsahin.foreignexchangeapplication.Controller;

import com.selimsahin.foreignexchangeapplication.Entity.ExhangeRate;
import com.selimsahin.foreignexchangeapplication.Service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
