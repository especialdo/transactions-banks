package com.transactions.customer.infraestructure.adapter.in.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transactions.customer.application.use_case.service.ClienteUseCase;
import com.transactions.customer.infraestructure.adapter.in.rest.mapper.ClienteRestMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteRestMapper mapper;
    private final ClienteUseCase clienteUseCase;

    @PostMapping
    public ResponseEntity<Void> transfer(@RequestBody @Valid ClienteRequest request) {
        clienteUseCase.create(mapper.toCommand(request));

        return ResponseEntity.ok().build();
    }

}
