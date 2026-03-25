package com.transactions.customer.infraestructure.adapter.in.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transactions.customer.domain.port.in.ClienteUseCase;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transfers")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteUseCase clienteUseCase;

    @PostMapping
    public ResponseEntity<Void> transfer(@RequestBody @Valid ClienteRequest request) {
        clienteUseCase.execute();
        return ResponseEntity.ok().build();
    }
}
