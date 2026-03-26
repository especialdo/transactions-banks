package com.transactions.bankaccounts.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.transactions.bankaccounts.domain.service.CuentaDomainService;

@Configuration
public class DomainConfig {
    @Bean
    public CuentaDomainService cuentaDomainService() {
        return new CuentaDomainService();
    }
}
