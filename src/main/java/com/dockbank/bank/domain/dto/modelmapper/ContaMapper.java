package com.dockbank.bank.domain.dto.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.dockbank.bank.domain.dto.ContaDTO;
import com.dockbank.bank.domain.dto.input.ContaInput;
import com.dockbank.bank.domain.model.Conta;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ContaMapper {
    private ModelMapper modelMapper;

    public Conta toEntity(ContaInput contaInput) {
        return modelMapper.map(contaInput, Conta.class);
    }

    public ContaDTO toDTO(Conta conta) {
        return modelMapper.map(conta, ContaDTO.class);
    }
}
