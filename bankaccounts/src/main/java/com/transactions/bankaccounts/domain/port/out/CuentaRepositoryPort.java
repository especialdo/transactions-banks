package com.transactions.bankaccounts.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.transactions.bankaccounts.domain.model.Cuenta;

public interface CuentaRepositoryPort {
    Cuenta guardar(Cuenta cuenta);

    Optional<Cuenta> buscarPorNumeroCuenta(String numeroCuenta);

    List<Cuenta> buscarPorClienteId(String clienteId);
}
