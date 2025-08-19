package com.banco.abc.proxy;

import com.banco.abc.proxy.dto.ApiExchangeRateResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@RegisterRestClient(configKey = "api-proxy")
public interface ExchangeRateClient {

    @GET
    @Path("/today.json")
    @Produces(MediaType.APPLICATION_JSON)
    ApiExchangeRateResponse getLatestRates();

    @GET
    @Path("/{fecha}.json")
    @Produces(MediaType.APPLICATION_JSON)
    ApiExchangeRateResponse getLatestRateByDate(String fecha);
}