package com.transactions.bankaccounts.infraestructure.adapter.out.persitence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sincronizacion_clientes")
@Getter
@Setter
@Builder
public class ClientSincronizeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false)
    private String clienteId;

    @Column(nullable = false)
    private String nombre;

    private Boolean estado;

}
