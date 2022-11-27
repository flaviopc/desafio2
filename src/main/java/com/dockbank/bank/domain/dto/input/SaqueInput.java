package com.dockbank.bank.domain.dto.input;

import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaqueInput {
    @Min(value = 1, message = "Só é permitido o saque a partir de R$ 1")
    private double valor;
}
