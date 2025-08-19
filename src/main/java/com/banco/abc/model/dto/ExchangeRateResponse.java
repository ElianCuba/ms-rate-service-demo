package com.banco.abc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateResponse {

    private String dni;
    private Double venta;
    private Double sunat;
    private Double compra;
    private String date;
    private LocalDateTime timestamp;
    private int remainingQueries;
    }