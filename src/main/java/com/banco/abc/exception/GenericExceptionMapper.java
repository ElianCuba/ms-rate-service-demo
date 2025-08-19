package com.banco.abc.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException exception) {
        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

        if (exception instanceof IllegalArgumentException) {
            status = Response.Status.BAD_REQUEST;
        }

        if (exception.getMessage() != null && exception.getMessage().contains("Máximo permitido")) {
            status = Response.Status.TOO_MANY_REQUESTS;
        }
        return Response
                .status(status)
                .entity(new ErrorResponse(
                        "Ocurrió un error",
                        exception.getMessage()
                ))
                .build();
    }

    public static class ErrorResponse {
        public String error;
        public String details;

        public ErrorResponse(String error, String details) {
            this.error = error;
            this.details = details;
        }
    }
}