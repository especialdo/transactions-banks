package com.transactions.customer.application.use_case.service.impl;

import org.springframework.stereotype.Service;

import com.transactions.customer.application.command.ClienteEventCommand;
import com.transactions.customer.application.command.CreateClientCommand;
import com.transactions.customer.application.command.UpdateClientCommand;
import com.transactions.customer.application.mapper.ClienteAppMapper;
import com.transactions.customer.application.use_case.service.ClienteUseCase;
import com.transactions.customer.application.use_case.service.EventPublisherUseCase;
import com.transactions.customer.domain.exception.ResourceNotFoundException;
import com.transactions.customer.domain.model.Cliente;
import com.transactions.customer.domain.port.out.ClienteRepositoryPort;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteService implements ClienteUseCase {

    private final ClienteAppMapper mapper;

    private final ClienteRepositoryPort clienteRepository;

    private final EventPublisherUseCase eventPublisherUseCase;

    @Override
    public Cliente create(CreateClientCommand command) {
        Cliente cliente = mapper.toDomain(command);
        Cliente saved = clienteRepository.create(cliente);

        ClienteEventCommand cmd = ClienteEventCommand.builder()
                .accion("CREAR")
                .clienteId(saved.getClienteId())
                .nombre(saved.getNombre())
                .estado(saved.getEstado()).build();

        eventPublisherUseCase.publicaCliente(cmd);

        return saved;

    }

    @Override
    public Cliente update(String id, UpdateClientCommand command) {
        Cliente cliente = mapper.updatetoDomain(command);
        return clienteRepository.update(id, cliente);
    }

    @Override
    public void delete(String id) {
        clienteRepository.delete(id);

        ClienteEventCommand cmd = ClienteEventCommand.builder()
                .accion("ELIMINAR")
                .clienteId(id)
                .nombre(null)
                .estado(null).build();

        eventPublisherUseCase.publicaCliente(cmd);
    }

    @Override
    public Cliente findById(String id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
    }

}
