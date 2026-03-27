package com.transactions.customer.application.use_case.service.impl;

import org.springframework.stereotype.Service;

import com.transactions.customer.application.command.CreateClientCommand;
import com.transactions.customer.application.command.UpdateClientCommand;
import com.transactions.customer.application.dtos.ClienteEvent;
import com.transactions.customer.application.mapper.ClienteAppMapper;
import com.transactions.customer.application.use_case.service.ClienteUseCase;
import com.transactions.customer.domain.exception.ResourceNotFoundException;
import com.transactions.customer.domain.model.Cliente;
import com.transactions.customer.domain.port.out.ClienteRepositoryPort;
import com.transactions.customer.infraestructure.adapter.out.kafka.ClienteProducer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteService implements ClienteUseCase {

    private final ClienteAppMapper mapper;

    private final ClienteProducer clienteProducer;

    private final ClienteRepositoryPort clienteRepository;

    @Override
    public Cliente create(CreateClientCommand command) {
        Cliente cliente = mapper.toDomain(command);
        Cliente saved = clienteRepository.create(cliente);

        // TODO: corregir esto
        ClienteEvent event = new ClienteEvent(
                saved.getClienteId(),
                saved.getNombre(),
                saved.getEstado(),
                "CREAR");
        clienteProducer.sendClienteEvent(event);

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
        // Publicar evento en Kafka
        ClienteEvent event = new ClienteEvent(
                id,
                null,
                null,
                "ELIMINAR");
        clienteProducer.sendClienteEvent(event);
    }

    @Override
    public Cliente findById(String id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
    }

}
