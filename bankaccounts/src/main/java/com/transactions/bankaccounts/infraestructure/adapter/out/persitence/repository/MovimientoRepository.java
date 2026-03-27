package com.transactions.bankaccounts.infraestructure.adapter.out.persitence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transactions.bankaccounts.infraestructure.adapter.out.persitence.entities.MovimientoEntity;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoEntity, String> {
    List<MovimientoEntity> findByCuentaIdAndFechaBetween(
            String cuentaId,
            LocalDateTime inicio,
            LocalDateTime fin);
}
