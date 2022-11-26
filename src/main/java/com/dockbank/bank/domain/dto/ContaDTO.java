package com.dockbank.bank.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaDTO {
    private Long id;
    private PessoaDTO pessoa;
    private double saldo;
}
