package com.transactions.customer.application.mapper;

import org.mapstruct.Mapper;

import com.transactions.customer.application.command.ClienteEventCommand;
import com.transactions.customer.domain.dto.ClienteEvent;

@Mapper(componentModel = "spring")
public interface PublishMapper {
    ClienteEvent toDtoDomain(ClienteEventCommand command);

}
