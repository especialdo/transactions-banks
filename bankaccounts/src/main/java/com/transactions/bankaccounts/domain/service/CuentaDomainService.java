package com.transactions.bankaccounts.domain.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.transactions.bankaccounts.domain.enums.EstadoCuenta;
import com.transactions.bankaccounts.domain.enums.TipoMovimiento;
import com.transactions.bankaccounts.domain.exception.CuentaInactivaException;
import com.transactions.bankaccounts.domain.exception.SaldoInsuficienteException;
import com.transactions.bankaccounts.domain.model.Cuenta;
import com.transactions.bankaccounts.domain.model.Movimiento;

public class CuentaDomainService {
    public Movimiento aplicarMovimiento(Cuenta cuenta, BigDecimal valor) {
        if (cuenta.getEstado() == EstadoCuenta.INACTIVA) {
            throw new CuentaInactivaException("Cuenta inactiva");
        }

        BigDecimal nuevoSaldo = cuenta.getSaldo().add(valor);

        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }

        cuenta.setSaldo(nuevoSaldo);

        Movimiento movimiento = new Movimiento();
        movimiento.setId(UUID.randomUUID().toString());
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setTipo(valor.compareTo(BigDecimal.ZERO) > 0
                ? TipoMovimiento.DEPOSITO
                : TipoMovimiento.RETIRO);
        movimiento.setValor(valor);
        movimiento.setSaldo(nuevoSaldo);

        return movimiento;
    }
}
