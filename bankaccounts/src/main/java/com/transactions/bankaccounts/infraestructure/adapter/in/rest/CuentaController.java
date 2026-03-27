package com.transactions.bankaccounts.infraestructure.adapter.in.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transactions.bankaccounts.application.use_case.CuentaUseCase;
import com.transactions.bankaccounts.domain.model.Cuenta;
import com.transactions.bankaccounts.infraestructure.adapter.in.rest.mapper.MovimientoRestMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cuenta")
@RequiredArgsConstructor
public class CuentaController {
    private final CuentaUseCase crearCuentaUseCase;
    private final MovimientoRestMapper mapper;

    @PostMapping
    public ResponseEntity<SuccessResponse> crear(@RequestBody @Valid CuentaRequest request) {

        Cuenta cuenta = crearCuentaUseCase.crearCuenta(mapper.toCommand(request));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.of("cuenta creada exitosamente con id: " + cuenta.getId()));

    }
}
