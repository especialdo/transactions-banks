package com.transactions.bankaccounts.domain.port.out;

public interface MovimientoEventPort {
    void publicarMovimiento(MovimientoEvent event);
}
