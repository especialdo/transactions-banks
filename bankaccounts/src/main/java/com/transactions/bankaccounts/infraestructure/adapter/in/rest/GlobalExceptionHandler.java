package com.transactions.bankaccounts.infraestructure.adapter.in.rest;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.transactions.bankaccounts.domain.exception.BusinessException;
import com.transactions.bankaccounts.domain.exception.ResourceNotFoundException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse> handleValidation(
                        MethodArgumentNotValidException ex, HttpServletRequest request) {

                List<String> errors = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                                .toList();

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(ErrorResponse.of("Error de validación", 400, errors, request.getRequestURI()));
        }

        // Recurso no encontrado (404)
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleNotFound(
                        ResourceNotFoundException ex, HttpServletRequest request) {

                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(ErrorResponse.of(ex.getMessage(), 404, List.of(), request.getRequestURI()));
        }

        // Excepciones de negocio
        @ExceptionHandler(BusinessException.class)
        public ResponseEntity<ErrorResponse> handleBusiness(
                        BusinessException ex, HttpServletRequest request) {

                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                                .body(ErrorResponse.of(ex.getMessage(), 422, List.of(), request.getRequestURI()));
        }

        // Violación de constraint de BD (duplicados, not-null)
        @ExceptionHandler(DataIntegrityViolationException.class)
        public ResponseEntity<ErrorResponse> handleDataIntegrity(
                        DataIntegrityViolationException ex, HttpServletRequest request) {

                String message = "Error de integridad de datos";

                if (ex.getMessage().contains("unique") || ex.getMessage().contains("Unique")) {
                        message = "Ya existe un registro con esos datos";
                } else if (ex.getMessage().contains("not-null") || ex.getMessage().contains("null value")) {
                        message = "Hay campos obligatorios sin valor";
                }

                return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body(ErrorResponse.of(message, 409, List.of(ex.getMostSpecificCause().getMessage()),
                                                request.getRequestURI()));
        }

        // Transacciones fallidas
        @ExceptionHandler(TransactionSystemException.class)
        public ResponseEntity<ErrorResponse> handleTransaction(
                        TransactionSystemException ex, HttpServletRequest request) {

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(ErrorResponse.of("Error en la transacción", 500,
                                                List.of(ex.getMostSpecificCause().getMessage()),
                                                request.getRequestURI()));
        }

        // Cualquier otra excepción no controlada
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleGeneric(
                        Exception ex, HttpServletRequest request) {

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(ErrorResponse.of("Error interno del servidor", 500, List.of(ex.getMessage()),
                                                request.getRequestURI()));
        }
}
