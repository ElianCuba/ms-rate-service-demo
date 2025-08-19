package com.banco.abc.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateRequest {

    @NotBlank(message = "DNI es obligatorio")
    @Size(min = 8, max = 20, message = "DNI debe tener entre 8 y 20 caracteres")
    @Pattern(regexp = "^[0-9]+$", message = "DNI debe contener solo números")
    private String dni;


    @Pattern(
            regexp = "^\\d{4}([-\\/])\\d{2}\\1\\d{2}$",
            message = "La fecha debe estar en formato yyyy-MM-dd o yyyy/MM/dd"
    )
    private String fecha;

    public void setFecha(String fecha) {
        if (fecha != null) {
            fecha = fecha.replace("/", "-"); // normalizar
        }
        this.fecha = fecha;
    }
}