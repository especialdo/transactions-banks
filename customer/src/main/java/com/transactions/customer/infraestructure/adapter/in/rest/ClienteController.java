package com.transactions.customer.infraestructure.adapter.in.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transactions.customer.application.use_case.service.ClienteUseCase;
import com.transactions.customer.domain.model.Cliente;
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
    public ResponseEntity<SuccessResponse> transfer(@RequestBody @Valid ClienteRequest request) {
        Cliente cliente = clienteUseCase.create(mapper.toCommand(request));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.of("Cliente creado exitosamente con id: " + cliente.getClienteId()));

    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> update(
            @PathVariable String id,
            @RequestBody @Valid UpdateClienteRequest request) {
        clienteUseCase.update(id, mapper.toUpdateCommand(request));
        return ResponseEntity
                .ok(SuccessResponse.of("Cliente actualizado exitosamente"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> delete(@PathVariable String id) {
        clienteUseCase.delete(id);
        return ResponseEntity
                .ok(SuccessResponse.of("Cliente eliminado exitosamente"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> findById(@PathVariable String id) {
        Cliente cliente = clienteUseCase.findById(id);
        return ResponseEntity.ok(mapper.toResponse(cliente));
    }

}
