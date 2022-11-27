package com.dockbank.bank.domain.exception;

public class SaldoInsuficienteException extends RuntimeException {

    private static final String SALDO_INSUFICIENTE = "Sua conta não possui saldo suficiente";

    public SaldoInsuficienteException() {
        super(SALDO_INSUFICIENTE);
    }

}
