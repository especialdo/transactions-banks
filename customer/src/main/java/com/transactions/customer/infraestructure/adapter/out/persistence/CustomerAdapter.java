package com.transactions.customer.infraestructure.adapter.out.persistence;

import org.springframework.stereotype.Component;

import com.transactions.customer.domain.model.Cliente;
import com.transactions.customer.domain.port.out.ClienteRepositoryPort;
import com.transactions.customer.infraestructure.adapter.out.persistence.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerAdapter implements ClienteRepositoryPort {

    private final ClienteRepository customerRepository;

    @Override
    public Cliente create(Cliente command) {
        return customerRepository.save(command);
    }

    @Override
    public Cliente update(String id, Cliente command) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Cliente findById(String id) {
        return customerRepository.findById(id).orElse(null);
    }

}
