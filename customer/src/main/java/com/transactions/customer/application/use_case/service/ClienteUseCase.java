package com.transactions.customer.application.use_case.service;

import com.transactions.customer.application.command.CreateClientCommand;
import com.transactions.customer.application.command.UpdateClientCommand;
import com.transactions.customer.domain.model.Cliente;

public interface ClienteUseCase {
    Cliente create(CreateClientCommand command);

    Cliente update(String id, UpdateClientCommand command);

    void delete(String id);

    Cliente findById(String id);
}
