package com.banco.abc.service;

import com.banco.abc.mapper.ResposeMapper;
import com.banco.abc.model.ExchangeRate;
import com.banco.abc.model.dto.ExchangeRateRequest;
import com.banco.abc.model.dto.ExchangeRateResponse;
import com.banco.abc.proxy.ExchangeRateClient;
import com.banco.abc.proxy.api.DefaultApi;
import com.banco.abc.proxy.dto.ApiExchangeRateResponse;
import com.banco.abc.repository.ExchangeRateRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ApplicationScoped
public class ExchangeRateServiceImpl implements  ExchangeRateService {

    private static final int MAX_QUERIES_PER_DAY = 10;

    @Inject
    @RestClient
    DefaultApi exchangeRateClient;

    @Inject
    ExchangeRateRepository exchangeRateRepository;

    @Override
    @Transactional
    public ExchangeRateResponse getExchangeRate(ExchangeRateRequest rateRequest) {
        validateDailyLimit(rateRequest.getDni());

        ApiExchangeRateResponse exchangeRate = fetchExchangeRateFromExternalAPI(rateRequest.getFecha());

        ExchangeRate query = new ExchangeRate();
        query.dni = rateRequest.getDni();
        query.compra = exchangeRate.getCompra();
        query.sunat =  exchangeRate.getSunat();
        query.venta = exchangeRate.getVenta();
        query.date = exchangeRate.getFecha();
        exchangeRateRepository.persist(query);
        Long todayQueries = exchangeRateRepository.countByDniAndDate(
                rateRequest.getDni(),
                LocalDate.now()
        );
        int remainingQueries = MAX_QUERIES_PER_DAY - todayQueries.intValue();

        return new ExchangeRateResponse(
                rateRequest.getDni(),
                exchangeRate.getCompra(),
                exchangeRate.getSunat(),
                exchangeRate.getVenta(),
                exchangeRate.getFecha(),
                LocalDateTime.now(),
                remainingQueries
        );
    }


    private void validateDailyLimit(String dni) {
        Long todayQueries = exchangeRateRepository.countByDniAndDate(dni, LocalDate.now());

        if (todayQueries != null && todayQueries >= MAX_QUERIES_PER_DAY) {
            throw new RuntimeException(  "Límite de consultas diarias excedido. Máximo permitido: " + MAX_QUERIES_PER_DAY);
        }
    }
    private ApiExchangeRateResponse fetchExchangeRateFromExternalAPI(String fecha) {
        if (fecha == null) {
            com.banco.abc.proxy.model.ApiExchangeRateResponse latestRates = exchangeRateClient.getLatestRates();
           return ResposeMapper.INSTANCE.apiExchangeRateResponseTo(latestRates);
        } else {
            com.banco.abc.proxy.model.ApiExchangeRateResponse latestRates = exchangeRateClient.getLatestRateByDate(fecha);
            return ResposeMapper.INSTANCE.apiExchangeRateResponseTo(latestRates);
        }
    }


    public Long getQueryCountByDni(String dni) {
        return exchangeRateRepository.countByDniAndDate(dni, LocalDate.now());
    }

    public int getRemainingQueries(String dni) {
        Long todayQueries = getQueryCountByDni(dni);
        return MAX_QUERIES_PER_DAY - todayQueries.intValue();
    }


}