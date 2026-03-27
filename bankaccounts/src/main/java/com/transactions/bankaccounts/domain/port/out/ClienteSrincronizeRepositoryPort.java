package com.transactions.bankaccounts.domain.port.out;

public interface ClienteSrincronizeRepositoryPort {
    void save(String clienteId,
            String nombre,
            Boolean estado);

    void delete(String id);

    Boolean clienteById(String id);

    String findNombeClient(String id);
}
