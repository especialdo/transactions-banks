package com.transactions.bankaccounts.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.transactions.bankaccounts.application.command.CrearCuentaCommand;
import com.transactions.bankaccounts.domain.model.Cuenta;

@Mapper(componentModel = "spring")
public interface CuentaMapper {
    @Mapping(target = "id", ignore = true) // se genera en otro lado
    @Mapping(source = "saldoInicial", target = "saldo")
    @Mapping(source = "estado", target = "estado")
    @Mapping(target = "movimientos", expression = "java(java.util.Collections.emptyList())")
    Cuenta toCuenta(CrearCuentaCommand command);
}
