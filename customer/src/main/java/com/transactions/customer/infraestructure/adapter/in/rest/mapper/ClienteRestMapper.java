package com.transactions.customer.infraestructure.adapter.in.rest.mapper;

import org.mapstruct.Mapper;

import com.transactions.customer.application.command.CreateClientCommand;
import com.transactions.customer.application.command.UpdateClientCommand;
import com.transactions.customer.domain.model.Cliente;
import com.transactions.customer.infraestructure.adapter.in.rest.ClienteRequest;
import com.transactions.customer.infraestructure.adapter.in.rest.ClienteResponse;
import com.transactions.customer.infraestructure.adapter.in.rest.UpdateClienteRequest;

@Mapper(componentModel = "spring")
public interface ClienteRestMapper {
    CreateClientCommand toCommand(ClienteRequest request);

    UpdateClientCommand toUpdateCommand(UpdateClienteRequest request);

    ClienteResponse toResponse(Cliente cliente);
}
