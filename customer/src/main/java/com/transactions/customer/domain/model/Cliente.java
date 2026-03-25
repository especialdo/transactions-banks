package com.transactions.customer.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Persona {
    private String clienteId;
    private String contrasena;
    private Boolean estado;
}
