package com.dockbank.bank.domain.dto.input;

import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositoInput {
    @Min(value = 1, message = "Só são aceitos depositos a partir de R$ 1")
    private double valor;
}
