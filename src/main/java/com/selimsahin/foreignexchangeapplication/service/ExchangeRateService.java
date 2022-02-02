package com.selimsahin.foreignexchangeapplication.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.selimsahin.foreignexchangeapplication.entity.ExhangeRate;
import com.selimsahin.foreignexchangeapplication.restAPI.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ExchangeRateService {

    @Autowired
    RestClient restClient;

    public ExhangeRate getExhangeRate(String symbols) throws Exception {
        try {
            Gson gson = new Gson();
            String response = restClient.getAPIRequest("&symbols=" + symbols);
            JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
            return gson.fromJson(jsonObject, ExhangeRate.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }

}
