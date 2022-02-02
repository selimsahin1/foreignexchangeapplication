package com.selimsahin.foreignexchangeapplication.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "conversion")
public class Conversion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    @Column(name = "createTime")
    private String createTime;
    @Column(name = "baseCurrency")
    private String baseCurrency;
    @Column(name = "baseAmount")
    private BigDecimal baseAmount;
    @Column(name = "targetCurrency")
    private String targetCurrency;
    @Column(name = "targetAmount")
    private BigDecimal targetAmount;
}
