package com.dockbank.bank.domain.exception;

public class ContaNaoEncontradaException extends RuntimeException {
    private static final String CONTA_NAO_EXISTE = "A conta de código %d não existe";

    public ContaNaoEncontradaException(String msg) {
        super(msg);
    }

    public ContaNaoEncontradaException(Long id) {
        this(String.format(CONTA_NAO_EXISTE, id));
    }
}
