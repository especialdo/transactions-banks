package com.transactions.customer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEvent {
    private String clienteId;
    private String nombre;
    private Boolean estado;
    private String accion;
}
