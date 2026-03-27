package com.transactions.bankaccounts.application.use_case;

import java.util.List;

import org.springframework.stereotype.Service;

import com.transactions.bankaccounts.application.command.CrearCuentaCommand;
import com.transactions.bankaccounts.domain.model.Cuenta;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CuentaService implements CuentaUseCase {

    @Override
    public Cuenta crearCuenta(CrearCuentaCommand command) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearCuenta'");
    }

    @Override
    public Cuenta obtenerPorNumeroCuenta(String numeroCuenta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPorNumeroCuenta'");
    }

    @Override
    public List<Cuenta> listarPorClienteId(String clienteId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarPorClienteId'");
    }

}
