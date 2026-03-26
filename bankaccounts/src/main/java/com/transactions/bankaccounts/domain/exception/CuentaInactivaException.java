package com.transactions.bankaccounts.domain.exception;

public class CuentaInactivaException extends RuntimeException {
    public CuentaInactivaException(String message) {
        super(message);
    }
}
