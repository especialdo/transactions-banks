package com.transactions.bankaccounts.application.use_case;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.transactions.bankaccounts.application.dto.MovimientoReporte;
import com.transactions.bankaccounts.domain.enums.EstadoCuenta;
import com.transactions.bankaccounts.domain.enums.TipoMovimiento;
import com.transactions.bankaccounts.domain.exception.CuentaInactivaException;
import com.transactions.bankaccounts.domain.exception.CuentaNotFoundException;
import com.transactions.bankaccounts.domain.exception.SaldoInsuficienteException;
import com.transactions.bankaccounts.domain.model.Cuenta;
import com.transactions.bankaccounts.domain.model.Movimiento;
import com.transactions.bankaccounts.domain.port.out.CuentaRepositoryPort;
import com.transactions.bankaccounts.domain.port.out.MovimientoEvent;
import com.transactions.bankaccounts.domain.port.out.MovimientoEventPort;
import com.transactions.bankaccounts.domain.port.out.MovimientoRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovimientoService implements MovimientoUseCase {

    private final CuentaRepositoryPort cuentaRepository;
    private final MovimientoRepositoryPort movimientoRepository;
    private final MovimientoEventPort eventPort;

    @Override
    public Movimiento registrarMovimiento(String numeroCuenta, BigDecimal valor) {
        Cuenta cuenta = cuentaRepository.buscarPorNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new CuentaNotFoundException("Cuenta no encontrada: " + numeroCuenta));

        if (cuenta.getEstado() == EstadoCuenta.INACTIVA) {
            throw new CuentaInactivaException("Cuenta inactiva");
        }

        BigDecimal saldoActual = cuenta.getSaldo();
        BigDecimal nuevoSaldo = saldoActual.add(valor);

        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new SaldoInsuficienteException("Saldo no disponible");
        }

        Movimiento movimiento = new Movimiento();
        movimiento.setId(UUID.randomUUID().toString());
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setTipo(valor.compareTo(BigDecimal.ZERO) > 0 ? TipoMovimiento.DEPOSITO : TipoMovimiento.RETIRO);
        movimiento.setValor(valor);
        movimiento.setSaldo(nuevoSaldo);

        cuenta.setSaldo(nuevoSaldo);
        cuentaRepository.guardar(cuenta);
        Movimiento saved = movimientoRepository.guardar(movimiento);

        // Publicar evento asíncrono a Kafka
        eventPort.publicarMovimiento(MovimientoEvent.builder()
                .cuentaId(cuenta.getId())
                .numeroCuenta(numeroCuenta)
                .valor(valor)
                .saldo(nuevoSaldo)
                .fecha(movimiento.getFecha())
                .build());

        return saved;
    }

    @Override
    public List<MovimientoReporte> reportePorFechasYCliente(LocalDate inicio, LocalDate fin, String clienteId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reportePorFechasYCliente'");
    }

}
