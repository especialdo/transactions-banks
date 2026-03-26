package com.transactions.bankaccounts.domain.port.out;

import java.time.LocalDateTime;
import java.util.List;

import com.transactions.bankaccounts.domain.model.Movimiento;

public interface MovimientoRepositoryPort {
    Movimiento guardar(Movimiento movimiento);

    List<Movimiento> buscarPorCuentaYFechas(String cuentaId, LocalDateTime inicio, LocalDateTime fin);
}
