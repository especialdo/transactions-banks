package com.transactions.bankaccounts.application.exception;

public class ClienteNoEncontradoException extends RuntimeException {
    public ClienteNoEncontradoException(String message) {
        super(message);
    }
}