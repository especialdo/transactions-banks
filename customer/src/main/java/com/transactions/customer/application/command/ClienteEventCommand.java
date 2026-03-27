package com.transactions.customer.application.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEventCommand {
    private String clienteId;
    private String nombre;
    private Boolean estado;
    private String accion;
}
