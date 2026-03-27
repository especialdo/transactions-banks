package com.transactions.bankaccounts.domain.port.out;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovimientoEvent {
    private String cuentaId;
    private String numeroCuenta;
    private BigDecimal valor;
    private BigDecimal saldo;
    private LocalDateTime fecha;
}