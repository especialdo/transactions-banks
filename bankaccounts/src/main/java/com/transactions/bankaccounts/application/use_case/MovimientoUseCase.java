package com.transactions.bankaccounts.application.use_case;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.transactions.bankaccounts.application.dto.MovimientoReporte;
import com.transactions.bankaccounts.domain.model.Movimiento;

public interface MovimientoUseCase {
    Movimiento registrarMovimiento(String numeroCuenta, BigDecimal valor);

    List<MovimientoReporte> reportePorFechasYCliente(LocalDate inicio, LocalDate fin, String clienteId);
}
