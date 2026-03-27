package com.transactions.customer.domain.port.out;

import com.transactions.customer.domain.dto.ClienteEvent;

public interface CustomerEventPublisherPort {
    void publicarCliente(ClienteEvent event);

}
