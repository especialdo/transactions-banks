package com.transactions.bankaccounts.infraestructure.adapter.out.persitence.exception;

public class CuentaNotFoundException extends RuntimeException {
    public CuentaNotFoundException(String message) {
        super(message);
    }
}
