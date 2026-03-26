package com.transactions.bankaccounts.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.transactions.bankaccounts.domain.enums.TipoMovimiento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movimiento {
    private String id;
    private LocalDateTime fecha;
    private TipoMovimiento tipo;
    private BigDecimal valor;
    private BigDecimal saldo;
}
