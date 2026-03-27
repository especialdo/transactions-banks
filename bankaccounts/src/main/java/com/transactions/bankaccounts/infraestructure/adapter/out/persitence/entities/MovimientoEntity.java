package com.transactions.bankaccounts.infraestructure.adapter.out.persitence.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.transactions.bankaccounts.domain.enums.TipoMovimiento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "movimientos")
@Getter
@Setter
public class MovimientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipo;

    @Column(precision = 15, scale = 2)
    private BigDecimal valor;

    @Column(precision = 15, scale = 2)
    private BigDecimal saldo; // saldo disponible DESPUÉS del movimiento

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id")
    private CuentaEntity cuenta;
}