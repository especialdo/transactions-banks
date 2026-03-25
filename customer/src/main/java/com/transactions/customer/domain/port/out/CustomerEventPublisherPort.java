package com.transactions.customer.domain.port.out;

import com.transactions.customer.domain.model.Cliente;

public interface CustomerEventPublisherPort {
    void publicarClienteCreado(Cliente cliente);

    void publicarClienteActualizado(Cliente cliente);
}
