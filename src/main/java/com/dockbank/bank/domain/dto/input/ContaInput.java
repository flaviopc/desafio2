package com.dockbank.bank.domain.dto.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaInput {
    @NotNull
    private PessoaInput pessoa;
    @PositiveOrZero
    private double saldo;
}
