package com.banco.abc.proxy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiExchangeRateResponse {

    @JsonProperty("fecha")
    private String fecha;

    @JsonProperty("sunat")
    private Double sunat;

    @JsonProperty("compra")
    private Double compra;

    @JsonProperty("venta")
    private Double venta;
}