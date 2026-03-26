package com.transactions.bankaccounts.infraestructure.adapter.out.persitence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.transactions.bankaccounts.domain.model.Cuenta;
import com.transactions.bankaccounts.domain.port.out.CuentaRepositoryPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CuentaAdapterPersistence implements CuentaRepositoryPort {

    @Override
    public Cuenta guardar(Cuenta cuenta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public Optional<Cuenta> buscarPorNumeroCuenta(String numeroCuenta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorNumeroCuenta'");
    }

    @Override
    public List<Cuenta> buscarPorClienteId(String clienteId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorClienteId'");
    }

}
