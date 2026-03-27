package com.transactions.bankaccounts.application.use_case;

import java.time.LocalDate;
import java.util.List;

import com.transactions.bankaccounts.application.dto.MovimientoReporte;

public interface ObtenerReporteUseCase {
    List<MovimientoReporte> ejecutar(String clienteId, LocalDate inicio, LocalDate fin);
}
