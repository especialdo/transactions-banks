package com.transactions.customer.application.use_case.service.impl;

import org.springframework.stereotype.Service;

import com.transactions.customer.application.command.ClienteEventCommand;
import com.transactions.customer.application.mapper.PublishMapper;
import com.transactions.customer.application.use_case.service.EventPublisherUseCase;
import com.transactions.customer.domain.dto.ClienteEvent;
import com.transactions.customer.domain.port.out.CustomerEventPublisherPort;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventPublisherService implements EventPublisherUseCase {

    private final CustomerEventPublisherPort publish;

    public final PublishMapper mapper;

    @Override
    public void publicaCliente(ClienteEventCommand command) {
        ClienteEvent c = mapper.toDtoDomain(command);
        publish.publicarCliente(c);
    }

}
