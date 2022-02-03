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


@Component
public class ExchangeRateService {

    @Autowired
    RestClient restClient;

    public ExhangeRate getExhangeRate(String symbols) throws CloudException {
        try {
            Gson gson = new Gson();
            String response = restClient.getAPIRequest("&symbols=" + symbols);
            JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
            ExhangeRate exhangeRate = gson.fromJson(jsonObject, ExhangeRate.class);
            if (exhangeRate.getRates().isEmpty()) {
                throw new CloudException(HttpExceptionEnum.HTTP_ENTER_CORRECT_CURRENCY);
            }
            return exhangeRate;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CloudException(HttpExceptionEnum.HTTP_OPERATION_FAILED);
        }
    }

}
