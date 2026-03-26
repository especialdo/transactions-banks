package com.transactions.customer.application.command;

public record CreateClientCommand(
        String identificacion,
        String nombre,
        String direccion,
        String telefono,
        String contrasena,
        Boolean estado) {

}
