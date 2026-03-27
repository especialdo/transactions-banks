package com.transactions.bankaccounts.application.use_case;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;

import com.transactions.bankaccounts.application.dto.MovimientoReporte;
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
    private final ClienteSrincronizeUseCase client;

    @Override
    public List<MovimientoReporte> ejecutar(String clienteId, LocalDate inicio, LocalDate fin) {
        // 1. Buscar todas las cuentas del cliente
        List<Cuenta> cuentas = cuentaRepository.buscarPorClienteId(clienteId);

        // 2. Para cada cuenta buscar movimientos en el rango
        String cliente = client.findNombreClient(clienteId);

        return cuentas.stream()
                .flatMap(cuenta -> {
                    List<Movimiento> movimientos = movimientoRepository.buscarPorCuentaYFechas(
                            cuenta.getId(),
                            inicio.atStartOfDay(),
                            fin.atTime(23, 59, 59));
                    return movimientos.stream().map(m -> MovimientoReporte.builder()
                            .fecha(m.getFecha())
                            .cliente(cliente)
                            .numeroCuenta(cuenta.getNumeroCuenta())
                            .tipo(cuenta.getTipoCuenta())
                            .saldoInicial(m.getSaldo().subtract(m.getValor()))
                            .estado(cuenta.getEstado())
                            .movimiento(m.getValor())
                            .saldoDisponible(m.getSaldo())
                            .build());
                })
                .sorted(Comparator.comparing(MovimientoReporte::getFecha).reversed())
                .toList();
    }
}
