package com.transactions.bankaccounts.application.use_case;

import org.springframework.stereotype.Service;

import com.transactions.bankaccounts.application.dto.ClientSincronize;
import com.transactions.bankaccounts.domain.port.out.ClienteSrincronizeRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteSrincronizeService implements ClienteSrincronizeUseCase {

    private final ClienteSrincronizeRepositoryPort client;

    @Override
    public void save(ClientSincronize params) {
        client.save(params.getClienteId(), params.getNombre(), params.getEstado());
    }

    @Override
    public void delete(String id) {
        client.delete(id);
    }

    @Override
    public Boolean clienteById(String id) {
        return client.clienteById(id);
    }

}
