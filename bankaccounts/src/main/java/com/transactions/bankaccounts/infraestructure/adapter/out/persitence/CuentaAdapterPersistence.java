package com.transactions.bankaccounts.infraestructure.adapter.out.persitence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.transactions.bankaccounts.domain.model.Cuenta;
import com.transactions.bankaccounts.domain.port.out.CuentaRepositoryPort;
import com.transactions.bankaccounts.infraestructure.adapter.out.persitence.entities.CuentaEntity;
import com.transactions.bankaccounts.infraestructure.adapter.out.persitence.mapper.CuentaMapper;
import com.transactions.bankaccounts.infraestructure.adapter.out.persitence.repository.CuentaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CuentaAdapterPersistence implements CuentaRepositoryPort {

    private final CuentaRepository repository;
    private final CuentaMapper mapper;

    @Override
    public Cuenta guardar(Cuenta cuenta) {
        CuentaEntity ce = mapper.toEntity(cuenta);
        CuentaEntity cePersist = repository.save(ce);
        return mapper.toDomain(cePersist);
    }

    @Override
    public Optional<Cuenta> buscarPorNumeroCuenta(String numeroCuenta) {
        return repository
                .findByNumeroCuenta(numeroCuenta)
                .map(mapper::toDomain);
    }

    @Override
    public List<Cuenta> buscarPorClienteId(String clienteId) {
        return repository.findByClienteId(clienteId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

}
