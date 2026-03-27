package com.transactions.bankaccounts.infraestructure.adapter.out.persitence.mapper;

import org.mapstruct.Mapper;

import com.transactions.bankaccounts.domain.model.Cuenta;
import com.transactions.bankaccounts.infraestructure.adapter.out.persitence.entities.CuentaEntity;

@Mapper(componentModel = "spring")
public interface CuentaPersistenceMapper {
    Cuenta toDomain(CuentaEntity entity);

    CuentaEntity toEntity(Cuenta domain);
}
