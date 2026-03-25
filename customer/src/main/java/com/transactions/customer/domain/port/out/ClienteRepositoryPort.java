package com.transactions.customer.domain.port.out;

import com.transactions.customer.domain.model.Cliente;

public interface ClienteRepositoryPort {
    Cliente create(Cliente command);

    Cliente update(String id, Cliente command);

    void delete(String id);

    Cliente findById(String id);
}
