package com.transactions.bankaccounts.infraestructure.adapter.out.persitence.mapper;

import org.mapstruct.Mapper;

import com.transactions.bankaccounts.domain.model.Movimiento;
import com.transactions.bankaccounts.infraestructure.adapter.out.persitence.entities.MovimientoEntity;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {

    MovimientoEntity toEntity(Movimiento movimiento);

    Movimiento toDomain(MovimientoEntity entity);
}
