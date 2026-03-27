package com.transactions.bankaccounts.domain.port.out;

import com.transactions.bankaccounts.domain.enums.TipoClienteEvento;

import lombok.Data;

@Data
public class ClienteEvent {
    private String clienteId;
    private TipoClienteEvento tipo;
    private String nombre;
}