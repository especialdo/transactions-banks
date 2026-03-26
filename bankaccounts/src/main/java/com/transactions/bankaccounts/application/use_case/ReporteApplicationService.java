package com.transactions.bankaccounts.application.use_case;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.transactions.bankaccounts.application.dto.MovimientoReporte;
import com.transactions.bankaccounts.domain.enums.EstadoCuenta;
import com.transactions.bankaccounts.domain.model.Cuenta;
import com.transactions.bankaccounts.domain.model.Movimiento;
import com.transactions.bankaccounts.domain.port.out.CuentaRepositoryPort;
import com.transactions.bankaccounts.domain.port.out.MovimientoRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReporteApplicationService implements ObtenerReporteUseCase {

    private final MovimientoRepositoryPort movimientoRepository;
    private final CuentaRepositoryPort cuentaRepository;
    // private final ClienteFeignClient clienteClient;

    @Override
    public List<MovimientoReporte> ejecutar(String clienteId, LocalDate inicio, LocalDate fin) {
        // 1. Buscar todas las cuentas del cliente
        List<Cuenta> cuentas = cuentaRepository.buscarPorClienteId(clienteId);

        // 2. Para cada cuenta buscar movimientos en el rango
        // ClienteDto cliente = clienteClient.obtenerPorId(clienteId);

        return cuentas.stream()
                .flatMap(cuenta -> {
                    List<Movimiento> movimientos = movimientoRepository.buscarPorCuentaYFechas(
                            cuenta.getId(),
                            inicio.atStartOfDay(),
                            fin.atTime(23, 59, 59));
                    return movimientos.stream().map(m -> MovimientoReporte.builder()
                            .fecha(m.getFecha())
                            // .cliente(cliente.getNombre())
                            .numeroCuenta(cuenta.getNumeroCuenta())
                            .tipo(cuenta.getTipoCuenta())
                            .saldoInicial(cuenta.getSaldo().subtract(m.getValor())) // saldo antes
                            .estado(cuenta.getEstado() == EstadoCuenta.ACTIVA)
                            .movimiento(m.getValor())
                            .saldoDisponible(m.getSaldo())
                            .build());
                })
                // .sorted(Comparator.comparing(MovimientoReporteResponse::getFecha).reversed())
                .collect(Collectors.toList());
    }
}
