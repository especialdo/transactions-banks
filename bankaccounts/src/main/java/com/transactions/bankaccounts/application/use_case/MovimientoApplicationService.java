package com.transactions.bankaccounts.application.use_case;

import org.springframework.stereotype.Service;

import com.transactions.bankaccounts.application.command.RegistrarMovimientoCommand;
import com.transactions.bankaccounts.domain.exception.CuentaNotFoundException;
import com.transactions.bankaccounts.domain.model.Cuenta;
import com.transactions.bankaccounts.domain.model.Movimiento;
import com.transactions.bankaccounts.domain.port.out.CuentaRepositoryPort;
import com.transactions.bankaccounts.domain.port.out.MovimientoEvent;
import com.transactions.bankaccounts.domain.port.out.MovimientoEventPort;
import com.transactions.bankaccounts.domain.port.out.MovimientoRepositoryPort;
import com.transactions.bankaccounts.domain.service.CuentaDomainService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovimientoApplicationService implements RegistrarMovimientoUseCase {

    private final CuentaRepositoryPort cuentaRepository;
    private final MovimientoRepositoryPort movimientoRepository;
    private final MovimientoEventPort eventoPort;

    private final CuentaDomainService cuentaDomainService;

    @Override
    public Movimiento ejecutar(RegistrarMovimientoCommand command) {
        // 1. obtener agregado
        Cuenta cuenta = cuentaRepository
                .buscarPorNumeroCuenta(command.getNumeroCuenta())
                .orElseThrow(() -> new CuentaNotFoundException(command.getNumeroCuenta()));

        // 2. delegar lógica al dominio
        Movimiento movimiento = cuentaDomainService.aplicarMovimiento(cuenta, command.getValor());

        // 3. persistir via puerto de salida
        cuentaRepository.guardar(cuenta);
        Movimiento guardado = movimientoRepository.guardar(movimiento);

        // 4. publicar evento Kafka via puerto de salida
        eventoPort.publicarMovimiento(MovimientoEvent.builder()
                .cuentaId(cuenta.getId())
                .numeroCuenta(command.getNumeroCuenta())
                .valor(command.getValor())
                .saldo(movimiento.getSaldo())
                .fecha(movimiento.getFecha())
                .build());

        return guardado;
    }

}
