package com.transactions.customer.infraestructure.adapter.in.rest;

public record UpdateClienteRequest(
        String identificacion,
        String nombre,
        String direccion,
        String telefono,
        String contrasena,
        Boolean estado) {

}
