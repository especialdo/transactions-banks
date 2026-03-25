package com.transactions.customer.domain.port.out;

import java.util.Optional;

import com.transactions.customer.domain.model.Cliente;

public interface ClienteRepositoryPort {
    Cliente create(Cliente cliente);

    Cliente update(String id, Cliente cliente);

    void delete(String id);

    Optional<Cliente> findById(String id);
}
