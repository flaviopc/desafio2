package com.dockbank.bank.commom.modelmapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.dockbank.bank.domain.dto.TransacaoDTO;
import com.dockbank.bank.domain.model.Transacao;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class TransacaoMapper {
    private ModelMapper modelMapper;

    public TransacaoDTO toDTO(Transacao transacao) {
        return modelMapper.map(transacao, TransacaoDTO.class);
    }

    public List<TransacaoDTO> toListDTO(List<Transacao> transacoes) {
        return transacoes.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
