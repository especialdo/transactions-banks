package com.transactions.bankaccounts.application.use_case;

import java.util.List;

import com.transactions.bankaccounts.application.command.CrearCuentaCommand;
import com.transactions.bankaccounts.domain.model.Cuenta;

public interface CuentaUseCase {
    Cuenta crearCuenta(CrearCuentaCommand command);

    Cuenta obtenerPorNumeroCuenta(String numeroCuenta);

    List<Cuenta> listarPorClienteId(String clienteId);
}
