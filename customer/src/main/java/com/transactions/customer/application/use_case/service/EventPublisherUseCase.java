package com.transactions.customer.application.use_case.service;

import com.transactions.customer.application.command.ClienteEventCommand;

public interface EventPublisherUseCase {
    void publicaCliente(ClienteEventCommand command);
}
