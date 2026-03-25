package com.transactions.customer.infraestructure.adapter.in.rest.mapper;

import org.mapstruct.Mapper;

import com.transactions.customer.application.command.CreateClientCommand;
import com.transactions.customer.infraestructure.adapter.in.rest.ClienteRequest;

@Mapper(componentModel = "spring")
public interface ClienteRestMapper {
    CreateClientCommand toCommand(ClienteRequest request);
}
