package com.transactions.customer.application.use_case.service.impl;

import org.springframework.stereotype.Service;

import com.transactions.customer.application.command.CreateClientCommand;
import com.transactions.customer.application.command.UpdateClientCommand;
import com.transactions.customer.application.mapper.ClienteAppMapper;
import com.transactions.customer.application.use_case.service.ClienteUseCase;
import com.transactions.customer.domain.model.Cliente;
import com.transactions.customer.domain.port.out.ClienteRepositoryPort;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteService implements ClienteUseCase {

    private final ClienteAppMapper mapper;

    private final ClienteRepositoryPort clienteRepository;

    @Override
    public Cliente create(CreateClientCommand command) {
        Cliente cliente = mapper.toDomain(command);
        return clienteRepository.create(cliente);
    }

    @Override
    public Cliente update(String id, UpdateClientCommand cliente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        clienteRepository.delete(id);
    }

    @Override
    public Cliente findById(String id) {
        return clienteRepository.findById(id);
    }

}
