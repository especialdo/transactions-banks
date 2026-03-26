package com.transactions.bankaccounts.application.use_case;

import com.transactions.bankaccounts.application.command.CrearCuentaCommand;
import com.transactions.bankaccounts.domain.model.Cuenta;

public interface CrearCuentaUseCase {
    Cuenta ejecutar(CrearCuentaCommand command);
}
