package com.transactions.bankaccounts.infraestructure.adapter.out.persitence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transactions.bankaccounts.infraestructure.adapter.out.persitence.entities.CuentaEntity;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity, String> {

}
