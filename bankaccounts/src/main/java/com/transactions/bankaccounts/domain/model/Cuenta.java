package com.transactions.bankaccounts.domain.model;

import java.math.BigDecimal;
import java.util.List;

import com.transactions.bankaccounts.domain.enums.EstadoCuenta;
import com.transactions.bankaccounts.domain.enums.TipoCuenta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cuenta {
    private String id;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;
    private BigDecimal saldo;
    private EstadoCuenta estado;
    private List<Movimiento> movimientos;
}
