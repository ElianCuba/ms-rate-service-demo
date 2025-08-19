package com.banco.abc.repository;

import com.banco.abc.model.ExchangeRate;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class ExchangeRateRepository implements PanacheRepository<ExchangeRate> {


    public  Long countByDniAndDate(String dni, LocalDate date) {
        return count("dni = ?1 and queryDate = ?2", dni, date);
    }

}
