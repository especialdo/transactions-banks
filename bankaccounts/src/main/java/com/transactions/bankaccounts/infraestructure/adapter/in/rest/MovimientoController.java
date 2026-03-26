package com.transactions.bankaccounts.infraestructure.adapter.in.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.transactions.bankaccounts.application.command.RegistrarMovimientoCommand;
import com.transactions.bankaccounts.application.use_case.ObtenerReporteUseCase;
import com.transactions.bankaccounts.application.use_case.RegistrarMovimientoUseCase;
import com.transactions.bankaccounts.domain.model.Movimiento;
import com.transactions.bankaccounts.infraestructure.adapter.in.rest.mapper.MovimientoRestMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movimiento")
@RequiredArgsConstructor
public class MovimientoController {
    private final RegistrarMovimientoUseCase registrarMovimientoUseCase;
    private final ObtenerReporteUseCase obtenerReporteUseCase;

    private final MovimientoRestMapper mapper;

    @PostMapping
    public ResponseEntity<Movimiento> registrar(@RequestBody MovimientoRequest request) {
        RegistrarMovimientoCommand command = new RegistrarMovimientoCommand(
                request.numeroCuenta(),
                request.valor());
        return ResponseEntity.ok(registrarMovimientoUseCase.ejecutar(command));
    }

    @GetMapping("/reporte")
    public ResponseEntity<List<MovimientoReporteResponse>> reporte(
            @RequestParam String clienteId,
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin) {

        // convertir a MovimientoReporteResponse
        return ResponseEntity
                .ok(mapper.toListResponse(obtenerReporteUseCase.ejecutar(clienteId, fechaInicio, fechaFin)));
    }
}
