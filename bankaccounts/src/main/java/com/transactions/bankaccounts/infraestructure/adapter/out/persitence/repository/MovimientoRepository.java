package com.transactions.bankaccounts.infraestructure.adapter.out.persitence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transactions.bankaccounts.infraestructure.adapter.out.persitence.entities.MovimientoEntity;

public interface MovimientoRepository extends JpaRepository<MovimientoEntity, String> {

}
