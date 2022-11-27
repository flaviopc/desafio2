package com.dockbank.bank.domain.dto.input;

import java.util.List;

import com.dockbank.bank.domain.dto.ContaDTO;
import com.dockbank.bank.domain.dto.TransacaoDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacoesContaDTO {
    private ContaDTO conta;
    private List<TransacaoDTO> transacoes;
}
