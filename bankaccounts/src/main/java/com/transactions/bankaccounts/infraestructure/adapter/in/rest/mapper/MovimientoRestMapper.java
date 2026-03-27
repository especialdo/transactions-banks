package com.transactions.bankaccounts.infraestructure.adapter.in.rest.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.transactions.bankaccounts.application.command.CrearCuentaCommand;
import com.transactions.bankaccounts.application.dto.MovimientoReporte;
import com.transactions.bankaccounts.infraestructure.adapter.in.rest.CuentaRequest;
import com.transactions.bankaccounts.infraestructure.adapter.in.rest.MovimientoReporteResponse;

@Mapper(componentModel = "spring")
public interface MovimientoRestMapper {
    List<MovimientoReporteResponse> toListResponse(List<MovimientoReporte> source);

    @Mapping(source = "tipo", target = "tipoCuenta")
    @Mapping(source = "cliente", target = "clienteId")
    CrearCuentaCommand toCommand(CuentaRequest request);
}
