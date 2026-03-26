package com.transactions.customer.infraestructure.adapter.in.rest;

public record ClienteResponse(String id,
        String nombre,
        String direccion,
        String telefono,
        Boolean estado) {

}
