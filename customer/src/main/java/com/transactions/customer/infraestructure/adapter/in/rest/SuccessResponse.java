package com.transactions.customer.infraestructure.adapter.in.rest;

import java.time.LocalDateTime;

public record SuccessResponse(
        String message,
        LocalDateTime timestamp) {

    public static SuccessResponse of(String message) {
        return new SuccessResponse(message, LocalDateTime.now());
    }
}