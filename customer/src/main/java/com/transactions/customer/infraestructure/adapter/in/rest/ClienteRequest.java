package com.transactions.customer.infraestructure.adapter.in.rest;

public record ClienteRequest(
        String nombre,
        String direccion,
        String telefono,
        String contrasena,
        Boolean estado) {

}
