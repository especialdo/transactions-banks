package com.transactions.customer.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.transactions.customer.application.command.CreateClientCommand;
import com.transactions.customer.domain.model.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteAppMapper {
    @Mapping(target = "clienteId", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "genero", ignore = true)
    @Mapping(target = "edad", ignore = true)
    @Mapping(target = "identificacion", ignore = true)
    Cliente toDomain(CreateClientCommand command);
}
