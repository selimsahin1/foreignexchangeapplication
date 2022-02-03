package com.selimsahin.foreignexchangeapplication.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionHistoryCriteriaRequest {
    private String transactionId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate transactionDate;
}