package com.selimsahin.foreignexchangeapplication.Entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;

@Data
public class ExhangeRate {
    private Boolean success;
    private Integer timestamp;
    private String base;
    private String date;
    private HashMap<String, BigDecimal> rates;
}
