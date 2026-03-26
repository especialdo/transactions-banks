package com.transactions.bankaccounts.infraestructure.adapter.in.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteTopic {
    private String clienteId;
    private String nombre;
    private Boolean estado;
    private String accion;
}
