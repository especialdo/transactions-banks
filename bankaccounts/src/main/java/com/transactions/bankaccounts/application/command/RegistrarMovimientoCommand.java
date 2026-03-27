package com.transactions.bankaccounts.application.command;

import java.math.BigDecimal;

import lombok.Value;

@Value
public class RegistrarMovimientoCommand {
    String numeroCuenta;
    BigDecimal valor;
}
