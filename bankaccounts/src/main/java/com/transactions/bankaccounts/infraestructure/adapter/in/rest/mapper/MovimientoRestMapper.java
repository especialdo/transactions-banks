package com.transactions.bankaccounts.infraestructure.adapter.in.rest.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import com.transactions.bankaccounts.application.dto.MovimientoReporte;
import com.transactions.bankaccounts.domain.model.Movimiento;
import com.transactions.bankaccounts.infraestructure.adapter.in.rest.MovimientoReporteResponse;
import com.transactions.bankaccounts.infraestructure.adapter.in.rest.MovimientoResponse;

@Mapper(componentModel = "spring")
public interface MovimientoRestMapper {
    List<MovimientoReporteResponse> toListResponse(List<MovimientoReporte> source);

    MovimientoResponse toSingleResponse(Movimiento mov);

}
