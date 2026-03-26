package com.transactions.customer.domain.model;

import lombok.Data;

@Data
public class Persona {
    private String id;
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
