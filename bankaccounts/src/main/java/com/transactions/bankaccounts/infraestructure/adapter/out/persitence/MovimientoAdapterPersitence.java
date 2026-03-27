package com.transactions.bankaccounts.infraestructure.adapter.out.persitence;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.transactions.bankaccounts.domain.model.Movimiento;
import com.transactions.bankaccounts.domain.port.out.MovimientoRepositoryPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MovimientoAdapterPersitence implements MovimientoRepositoryPort {

    @Override
    public Movimiento guardar(Movimiento movimiento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public List<Movimiento> buscarPorCuentaYFechas(String cuentaId, LocalDateTime inicio, LocalDateTime fin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorCuentaYFechas'");
    }

}
