package com.transactions.bankaccounts.infraestructure.adapter.out.persitence;

import org.springframework.stereotype.Component;

import com.transactions.bankaccounts.domain.port.out.ClienteSrincronizeRepositoryPort;
import com.transactions.bankaccounts.infraestructure.adapter.out.persitence.entities.ClientSincronizeEntity;
import com.transactions.bankaccounts.infraestructure.adapter.out.persitence.repository.ClienteSincronizeRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClienteSrincronizeAdapter implements ClienteSrincronizeRepositoryPort {

    private final ClienteSincronizeRepository repository;

    @Override
    public void save(String clienteId, String nombre, Boolean estado) {
        ClientSincronizeEntity c = ClientSincronizeEntity.builder()
                .clienteId(clienteId)
                .nombre(nombre)
                .estado(estado)
                .build();
        repository.save(c);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public Boolean clienteById(String id) {
        return repository.findById(id).isPresent();
    }

}
