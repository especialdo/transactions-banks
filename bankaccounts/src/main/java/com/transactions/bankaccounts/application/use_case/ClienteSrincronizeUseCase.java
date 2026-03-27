package com.transactions.bankaccounts.application.use_case;

import com.transactions.bankaccounts.application.dto.ClientSincronize;

public interface ClienteSrincronizeUseCase {
    void save(ClientSincronize params);

    void delete(String id);

    Boolean clienteById(String id);
}
