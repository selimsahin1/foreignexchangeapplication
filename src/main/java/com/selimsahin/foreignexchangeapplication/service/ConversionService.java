package com.selimsahin.foreignexchangeapplication.service;

import com.selimsahin.foreignexchangeapplication.entity.Conversion;
import com.selimsahin.foreignexchangeapplication.entity.ExhangeRate;
import com.selimsahin.foreignexchangeapplication.repository.ConversionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ConversionService {

    @Autowired
    ExchangeRateService exchangeRateService;

    @Autowired
    ConversionRepository conversionRepository;

    public Conversion getConversion(BigDecimal amount, String targetCurrency) throws Exception {
        ExhangeRate exhangeRate = exchangeRateService.getExhangeRate(targetCurrency);
        HashMap.Entry<String, BigDecimal> entry = exhangeRate.getRates().entrySet().iterator().next();
        BigDecimal convertedAmount = entry.getValue().multiply(amount);

        Conversion conversion = saveConversion(amount, targetCurrency, convertedAmount);
        conversionRepository.save(conversion);

        return conversion;
    }

    public List<Conversion> getConversionList(String transactionDate,
                                              Long conversionId,
                                              Integer pageNumber,
                                              Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        if (conversionId != null) {
            return conversionRepository.findAllById(conversionId);
        } else
            return conversionRepository.findAllByCreateTimeContains(transactionDate);
    }

    private Conversion saveConversion(BigDecimal amount, String targetCurrency, BigDecimal convertedAmount) throws Exception {
        Conversion conversion = new Conversion();
        conversion.setBaseCurrency("EUR");
        conversion.setBaseAmount(amount);
        conversion.setTargetCurrency(targetCurrency);
        conversion.setTargetAmount(convertedAmount);
        conversion.setCreateTime(getCurrentTimeStamp());
        return conversion;
    }

    private static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date now = new Date();
        return sdfDate.format(now);
    }
}
