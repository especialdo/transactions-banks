package com.transactions.customer.infraestructure.adapter.in.rest;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(String message,
        int status,
        List<String> errors,
        String path,
        LocalDateTime timestamp) {
    public static ErrorResponse of(String message, int status, List<String> errors, String path) {
        return new ErrorResponse(message, status, errors, path, LocalDateTime.now());
    }
}
