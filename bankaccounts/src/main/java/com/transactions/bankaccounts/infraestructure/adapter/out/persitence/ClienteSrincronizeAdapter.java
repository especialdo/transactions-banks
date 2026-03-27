package com.transactions.bankaccounts.infraestructure.adapter.out.persitence;

import org.springframework.stereotype.Component;

import com.transactions.bankaccounts.domain.port.out.ClienteSrincronizeRepositoryPort;
import com.transactions.bankaccounts.infraestructure.adapter.out.persitence.entities.ClientSincronizeEntity;
import com.transactions.bankaccounts.infraestructure.adapter.out.persitence.repository.ClienteSincronizeRepository;

import jakarta.transaction.Transactional;
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
    @Transactional
    public void delete(String id) {
        repository.deleteByClienteId(id);
    }

    @Override
    public Boolean clienteById(String id) {
        return repository.findByClienteId(id).isPresent();
    }

    @Override
    public String findNombeClient(String id) {
        ClientSincronizeEntity c = repository.findByClienteId(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
        return c.getNombre();
    }

}
