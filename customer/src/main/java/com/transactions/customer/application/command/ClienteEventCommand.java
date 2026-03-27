package com.transactions.customer.application.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteEventCommand {
    private String clienteId;
    private String nombre;
    private Boolean estado;
    private String accion;
}
