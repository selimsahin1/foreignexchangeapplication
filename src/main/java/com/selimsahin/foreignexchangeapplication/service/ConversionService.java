package com.selimsahin.foreignexchangeapplication.service;

import com.selimsahin.foreignexchangeapplication.entity.Conversion;
import com.selimsahin.foreignexchangeapplication.exception.CloudException;
import com.selimsahin.foreignexchangeapplication.exception.HttpExceptionEnum;
import com.selimsahin.foreignexchangeapplication.repository.ConversionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class ConversionService {

    @Autowired
    ExchangeRateService exchangeRateService;

    @Autowired
    ConversionRepository conversionRepository;

    public Conversion getConversion(BigDecimal sourceAmount, String sourceCurrency, String targetCurrency) throws CloudException {
        BigDecimal exhangeRate = exchangeRateService.getExhangeRate(sourceCurrency, targetCurrency);
        BigDecimal targetAmount = exhangeRate.multiply(sourceAmount);
        Conversion conversion = saveConversion(sourceAmount, sourceCurrency, targetCurrency, targetAmount);
        try {
            conversionRepository.save(conversion);
            return conversion;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CloudException(HttpExceptionEnum.HTTP_CONVERSION_DATA_DOES_NOT_SAVED);
        }
    }

    public Page<Conversion> getConversionList(LocalDate transactionDate,
                                              String transactionId,
                                              Pageable pageable) throws CloudException {
        try {
            if (transactionId != null) {
                return conversionRepository.findAllByTransactionId(transactionId, pageable);
            } else
                return conversionRepository.findAllByCreateTime(transactionDate, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CloudException(HttpExceptionEnum.HTTP_CONVERTION_LIST_HAS_ERROR);
        }
    }

    private Conversion saveConversion(BigDecimal sourceAmount,
                                      String sourceCurrency,
                                      String targetCurrency,
                                      BigDecimal targetAmount) throws CloudException {
        Conversion conversion = new Conversion();
        conversion.setTransactionId(UUID.randomUUID().toString());
        conversion.setSourceCurrency(sourceCurrency);
        conversion.setSourceAmount(sourceAmount);
        conversion.setTargetCurrency(targetCurrency);
        conversion.setTargetAmount(targetAmount);
        conversion.setCreateTime(LocalDate.now());
        return conversion;
    }
}