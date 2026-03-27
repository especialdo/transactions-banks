package com.transactions.bankaccounts.infraestructure.adapter.in.rest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.transactions.bankaccounts.domain.enums.TipoCuenta;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovimientoReporteResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "d/M/yyyy")
    private LocalDateTime fecha;
    private String cliente;
    private String numeroCuenta;
    private TipoCuenta tipo;
    private BigDecimal saldoInicial;
    private boolean estado;
    private BigDecimal movimiento;
    private BigDecimal saldoDisponible;
}
