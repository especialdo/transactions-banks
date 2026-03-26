package com.transactions.bankaccounts.application.use_case;

import com.transactions.bankaccounts.application.command.RegistrarMovimientoCommand;
import com.transactions.bankaccounts.domain.model.Movimiento;

public interface RegistrarMovimientoUseCase {
    Movimiento ejecutar(RegistrarMovimientoCommand command);
}
