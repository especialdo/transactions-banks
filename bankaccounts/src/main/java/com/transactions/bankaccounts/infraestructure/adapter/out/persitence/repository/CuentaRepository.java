package com.transactions.bankaccounts.infraestructure.adapter.out.persitence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transactions.bankaccounts.infraestructure.adapter.out.persitence.entities.CuentaEntity;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity, String> {
    Optional<CuentaEntity> findByNumeroCuenta(String numeroCuenta);

    List<CuentaEntity> findByClienteId(String clienteId);
}
