package com.transactions.bankaccounts.application.command;

import java.math.BigDecimal;

import com.transactions.bankaccounts.domain.enums.TipoCuenta;

import lombok.Value;

@Value
public class CrearCuentaCommand {
    String numeroCuenta;
    TipoCuenta tipoCuenta;
    BigDecimal saldoInicial;
    String clienteId;
    Boolean estado;
}
