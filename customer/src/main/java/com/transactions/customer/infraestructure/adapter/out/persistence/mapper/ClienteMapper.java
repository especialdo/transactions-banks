package com.transactions.customer.infraestructure.adapter.out.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.transactions.customer.domain.model.Cliente;
import com.transactions.customer.infraestructure.adapter.out.persistence.entities.ClienteEntity;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    @Mapping(target = "id", source = "clienteId")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "direccion", source = "direccion")
    @Mapping(target = "telefono", source = "telefono")
    @Mapping(target = "contrasena", source = "contrasena")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "genero", ignore = true)
    @Mapping(target = "edad", ignore = true)
    @Mapping(target = "identificacion", ignore = true)
    ClienteEntity toEntity(Cliente domain);

    @Mapping(target = "clienteId", source = "id")
    Cliente toDomain(ClienteEntity entity);
}
