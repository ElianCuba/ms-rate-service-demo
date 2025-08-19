package com.banco.abc.service;

import com.banco.abc.model.dto.ExchangeRateRequest;
import com.banco.abc.model.dto.ExchangeRateResponse;

public interface ExchangeRateService {
     ExchangeRateResponse getExchangeRate(ExchangeRateRequest rateRequest);
     Long getQueryCountByDni(String dni);
     int getRemainingQueries(String dni);
}
