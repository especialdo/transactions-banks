package com.transactions.customer.application.command;

public record UpdateClientCommand(
        String identificacion,
        String nombre,
        String direccion,
        String telefono,
        String contrasena,
        Boolean estado) {

}
