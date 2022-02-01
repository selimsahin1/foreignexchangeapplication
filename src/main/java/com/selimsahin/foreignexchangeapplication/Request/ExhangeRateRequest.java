package com.selimsahin.foreignexchangeapplication.Request;

import lombok.Data;

import java.util.List;

@Data
public class ExhangeRateRequest {
    private String base;
    private List<String> retes;
}
