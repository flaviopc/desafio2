package com.dockbank.bank.domain.dto.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaInput {
    @NotNull
    private Long id;
}
