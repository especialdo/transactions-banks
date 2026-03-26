package com.transactions.bankaccounts.infraestructure.adapter.in.rest;

import java.math.BigDecimal;

public record MovimientoRequest(String numeroCuenta,
        BigDecimal valor) {

}
