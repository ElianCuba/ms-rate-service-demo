package com.banco.abc.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "exchange_rate")
public class ExchangeRate extends PanacheEntity {
    @Column(name = "dni", nullable = false, length = 8)
    public String dni;

    @Column(name = "venta")
    public Double venta;

    @Column(name = "sunat")
    public Double sunat;

    @Column(name = "compra")
    public Double compra;

    @Column(name = "date")
    public String date;

    @Column(name = "queryDate")
    public LocalDate queryDate;

    @Column(name = "created_at")
    public LocalDateTime createdAt;




    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.queryDate = LocalDate.now();
    }


}
