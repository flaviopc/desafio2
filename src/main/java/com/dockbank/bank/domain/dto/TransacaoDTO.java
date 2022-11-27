package com.dockbank.bank.domain.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoDTO {
    private Long idTransacao;
    private double valor;
    private LocalDateTime dataTransacao;
}
