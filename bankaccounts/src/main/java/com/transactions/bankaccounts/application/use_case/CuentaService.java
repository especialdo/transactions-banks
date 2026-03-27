package com.transactions.bankaccounts.application.use_case;

import java.util.List;

import org.springframework.stereotype.Service;

import com.transactions.bankaccounts.application.command.CrearCuentaCommand;
import com.transactions.bankaccounts.application.exception.ClienteNoEncontradoException;
import com.transactions.bankaccounts.application.mapper.CuentaMapper;
import com.transactions.bankaccounts.domain.model.Cuenta;
import com.transactions.bankaccounts.domain.port.out.ClienteSrincronizeRepositoryPort;
import com.transactions.bankaccounts.domain.port.out.CuentaRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CuentaService implements CuentaUseCase {

    private final CuentaRepositoryPort cuentaRepositoryPort;
    private final ClienteSrincronizeRepositoryPort clienteSrincronizeRepositoryPort;
    private final CuentaMapper mapper;

    @Override
    public Cuenta crearCuenta(CrearCuentaCommand command) {
        boolean existeCliente = clienteSrincronizeRepositoryPort
                .clienteById(command.getClienteId());

        if (!existeCliente) {
            throw new ClienteNoEncontradoException(
                    "El cliente con id " + command.getClienteId() + " no existe");
        }

        Cuenta cuenta = mapper.toCuenta(command);
        return cuentaRepositoryPort.guardar(cuenta);

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
