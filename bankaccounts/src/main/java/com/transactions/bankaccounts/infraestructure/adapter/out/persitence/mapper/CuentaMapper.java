package com.transactions.bankaccounts.infraestructure.adapter.out.persitence.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.transactions.bankaccounts.domain.model.Cuenta;
import com.transactions.bankaccounts.infraestructure.adapter.out.persitence.entities.CuentaEntity;

@Component
public class CuentaMapper {
    public Cuenta toDomain(CuentaEntity entity) {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(entity.getId());
        cuenta.setNumeroCuenta(entity.getNumeroCuenta());
        cuenta.setTipoCuenta(entity.getTipoCuenta());
        cuenta.setSaldo(entity.getSaldo());
        cuenta.setEstado(entity.getEstado());
        return cuenta;
    }

    public CuentaEntity toEntity(Cuenta domain, String clienteId) {
        CuentaEntity entity = new CuentaEntity();
        entity.setId(domain.getId() != null ? domain.getId() : UUID.randomUUID().toString());
        entity.setNumeroCuenta(domain.getNumeroCuenta());
        entity.setTipoCuenta(domain.getTipoCuenta());
        entity.setSaldo(domain.getSaldo());
        entity.setEstado(domain.getEstado());
        entity.setClienteId(clienteId);
        return entity;
    }
}
