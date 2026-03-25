package com.transactions.customer.infraestructure.adapter.out.persistence;

import org.springframework.stereotype.Component;

import com.transactions.customer.domain.model.Cliente;
import com.transactions.customer.domain.port.out.ClienteRepositoryPort;
import com.transactions.customer.infraestructure.adapter.out.persistence.entities.ClienteEntity;
import com.transactions.customer.infraestructure.adapter.out.persistence.mapper.ClienteMapper;
import com.transactions.customer.infraestructure.adapter.out.persistence.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerAdapter implements ClienteRepositoryPort {

    private final ClienteRepository customerRepository;
    private final ClienteMapper mapper;

    @Override
    public Cliente create(Cliente command) {
        ClienteEntity entity = mapper.toEntity(command);
        ClienteEntity persist = customerRepository.save(entity);
        return mapper.toDomain(persist);
    }

    @Override
    public Cliente update(String id, Cliente command) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Cliente findById(String id) {
        ClienteEntity find = customerRepository.findById(id).orElse(null);
        return mapper.toDomain(find);
    }

}
