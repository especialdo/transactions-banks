package com.transactions.customer.infraestructure.adapter.out.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.transactions.customer.domain.exception.ResourceNotFoundException;
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
        ClienteEntity entity = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));

        entity.setNombre(command.getNombre());
        entity.setDireccion(command.getDireccion());
        entity.setTelefono(command.getTelefono());
        entity.setContrasena(command.getContrasena());
        entity.setEstado(command.getEstado());
        entity.setIdentificacion(command.getIdentificacion());

        ClienteEntity updated = customerRepository.save(entity);
        return mapper.toDomain(updated);
    }

    @Override
    public void delete(String id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Optional<Cliente> findById(String id) {
        return customerRepository.findById(id).map(mapper::toDomain);
    }

}
