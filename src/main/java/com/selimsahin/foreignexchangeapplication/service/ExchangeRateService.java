package com.selimsahin.foreignexchangeapplication.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.selimsahin.foreignexchangeapplication.entity.ExhangeRate;
import com.selimsahin.foreignexchangeapplication.exception.CloudException;
import com.selimsahin.foreignexchangeapplication.exception.HttpExceptionEnum;
import com.selimsahin.foreignexchangeapplication.restAPI.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Component
public class ExchangeRateService {

    @Autowired
    RestClient restClient;

    public BigDecimal getExhangeRate(String source, String target) throws CloudException {
        try {
            Gson gson = new Gson();
            String response = restClient.getAPIRequest("&symbols=" + source + "," + target);
            JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
            ExhangeRate exhangeRate = gson.fromJson(jsonObject, ExhangeRate.class);
            BigDecimal sourceCurrency = exhangeRate.getRates().get(source);
            BigDecimal targetCurrency = exhangeRate.getRates().get(target);
            if (exhangeRate.getRates().isEmpty()) {
                throw new CloudException(HttpExceptionEnum.HTTP_ENTER_CORRECT_CURRENCY);
            }
            return targetCurrency.divide(sourceCurrency, 6, RoundingMode.HALF_UP);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CloudException(HttpExceptionEnum.HTTP_OPERATION_FAILED);
        }
    }
}
