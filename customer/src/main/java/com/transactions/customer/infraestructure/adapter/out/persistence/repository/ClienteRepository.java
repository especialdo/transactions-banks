package com.transactions.customer.infraestructure.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transactions.customer.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {

}
