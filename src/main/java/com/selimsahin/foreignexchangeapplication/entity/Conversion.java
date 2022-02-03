package com.selimsahin.foreignexchangeapplication.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "conversion")
public class Conversion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "transactionId")
    private String transactionId;
    @Column(name = "createTime")
    private LocalDate createTime;
    @Column(name = "sourceCurrency")
    private String sourceCurrency;
    @Column(name = "sourceAmount")
    private BigDecimal sourceAmount;
    @Column(name = "targetCurrency")
    private String targetCurrency;
    @Column(name = "targetAmount")
    private BigDecimal targetAmount;
}
