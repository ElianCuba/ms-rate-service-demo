package com.banco.abc.resourse;


import com.banco.abc.model.dto.ExchangeRateRequest;
import com.banco.abc.model.dto.ExchangeRateResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/api/v1/exchange-rate")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ExchangeRateResouse {
    @GET
    @Operation(summary = "Consultar tipo de cambio",
            description = "Obtiene el tipo de cambio")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Consulta exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExchangeRateResponse.class))),
            @APIResponse(responseCode = "400", description = "Parámetros inválidos"),
            @APIResponse(responseCode = "429", description = "Límite de consultas excedido"),
    })
    Response getExchangeRate(
            @QueryParam("dni")
            @NotBlank(message = "DNI es obligatorio")
            @Size(min = 8, max = 8, message = "DNI debe tener  8 caracteres")
            @Pattern(regexp = "^[0-9]+$", message = "DNI debe contener solo números")
            String dni,
            @QueryParam("fecha")
            @Pattern(
                    regexp = "^\\d{4}([-\\/])\\d{2}\\1\\d{2}$",
                    message = "La fecha debe estar en formato yyyy-MM-dd o yyyy/MM/dd"
            ) String fecha
            );

    @GET
    @Path("/queries/count/{dni}")
    @Operation(summary = "Consultar número de queries por DNI",
            description = "Obtiene el número de consultas realizadas hoy por un cliente")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Consulta exitosa"),
            @APIResponse(responseCode = "400", description = "DNI inválido")
    })
     Response getQueryCount(
            @PathParam("dni")
            @NotBlank(message = "DNI es obligatorio")
            @Size(min = 8, max = 8, message = "DNI debe tener  8 caracteres")
            @Pattern(regexp = "^[0-9]+$", message = "DNI debe contener solo números")
            String dni);
}
