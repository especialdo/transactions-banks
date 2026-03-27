package com.transactions.bankaccounts.infraestructure.adapter.in.rest.mapper;

import org.mapstruct.Mapping;

import com.transactions.bankaccounts.application.command.CrearCuentaCommand;
import com.transactions.bankaccounts.infraestructure.adapter.in.rest.CuentaRequest;

public interface CuentaRestMapper {
    @Mapping(source = "tipo", target = "tipoCuenta")
    @Mapping(source = "cliente", target = "clienteId")
    CrearCuentaCommand toCommand(CuentaRequest request);
}
