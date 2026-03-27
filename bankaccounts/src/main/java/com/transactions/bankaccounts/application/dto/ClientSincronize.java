package com.transactions.bankaccounts.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientSincronize {
    private String clienteId;
    private String nombre;
    private Boolean estado;
}
