package com.transactions.bankaccounts.infraestructure.adapter.out.persitence;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.transactions.bankaccounts.domain.model.Cuenta;
import com.transactions.bankaccounts.domain.model.Movimiento;
import com.transactions.bankaccounts.domain.port.out.MovimientoRepositoryPort;
import com.transactions.bankaccounts.infraestructure.adapter.out.persitence.entities.CuentaEntity;
import com.transactions.bankaccounts.infraestructure.adapter.out.persitence.entities.MovimientoEntity;
import com.transactions.bankaccounts.infraestructure.adapter.out.persitence.mapper.CuentaMapper;
import com.transactions.bankaccounts.infraestructure.adapter.out.persitence.mapper.MovimientoMapper;
import com.transactions.bankaccounts.infraestructure.adapter.out.persitence.repository.MovimientoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MovimientoAdapterPersitence implements MovimientoRepositoryPort {

    private final MovimientoRepository repository;

    private final MovimientoMapper mapper;

    private final CuentaMapper cuentaMapper;

    @Override
    public Movimiento guardar(Movimiento movimiento, Cuenta cuenta) {
        MovimientoEntity m = mapper.toEntity(movimiento);
        CuentaEntity c = cuentaMapper.toEntity(cuenta);
        m.setCuenta(c);
        MovimientoEntity persist = repository.save(m);
        return mapper.toDomain(persist);
    }

    @Override
    public List<Movimiento> buscarPorCuentaYFechas(String cuentaId, LocalDateTime inicio, LocalDateTime fin) {
        return repository
                .findByCuentaIdAndFechaBetween(cuentaId, inicio, fin)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

}
