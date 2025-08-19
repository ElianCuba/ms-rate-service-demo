package com.banco.abc.resourse;

import com.banco.abc.model.dto.ExchangeRateRequest;
import com.banco.abc.model.dto.ExchangeRateResponse;
import com.banco.abc.service.ExchangeRateService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@ApplicationScoped
public class ExchangeRateResouseImpl implements ExchangeRateResouse {

    @Inject
    ExchangeRateService exchangeRateService;


    @Override
    public Response getExchangeRate(String dni, String fecha) {
        ExchangeRateRequest request = new ExchangeRateRequest(dni,fecha);
        ExchangeRateResponse response = exchangeRateService.getExchangeRate(request);

        return Response.ok(response).build();
    }

    @Override
    public Response getQueryCount(String dni) {
        Long queryCount = exchangeRateService.getQueryCountByDni(dni);
        int remainingQueries = exchangeRateService.getRemainingQueries(dni);

        var response = Map.of(
                "dni", dni,
                "todayQueries", queryCount,
                "remainingQueries", remainingQueries,
                "maxDailyQueries", 10
        );

        return Response.ok(response).build();
    }

}
