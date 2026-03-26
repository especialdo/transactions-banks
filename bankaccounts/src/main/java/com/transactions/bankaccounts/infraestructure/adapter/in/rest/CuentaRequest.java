package com.transactions.bankaccounts.infraestructure.adapter.in.rest;

import java.math.BigDecimal;

import com.transactions.bankaccounts.domain.enums.TipoCuenta;

public record CuentaRequest(
        String numeroCuenta,
        TipoCuenta tipo,
        BigDecimal saldoInicial,
        Boolean estado,
        String cliente) {

}
