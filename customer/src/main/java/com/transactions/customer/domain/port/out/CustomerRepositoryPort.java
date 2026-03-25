package com.transactions.customer.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.transactions.customer.domain.model.Cliente;

public interface CustomerRepositoryPort {
    Cliente guardar(Cliente cliente);

    Optional<Cliente> buscarPorId(String clienteId);

    Optional<Cliente> buscarPorIdentificacion(String identificacion);

    List<Cliente> listarTodos();

    void eliminar(String clienteId);
}
