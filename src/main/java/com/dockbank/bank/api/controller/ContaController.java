package com.dockbank.bank.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dockbank.bank.domain.dto.ContaDTO;
import com.dockbank.bank.domain.dto.input.ContaInput;
import com.dockbank.bank.domain.dto.modelmapper.ContaMapper;
import com.dockbank.bank.domain.service.ContaService;

import lombok.AllArgsConstructor;

@RequestMapping("/contas")
@RestController
@AllArgsConstructor
public class ContaController {
    private ContaService contaService;
    private ContaMapper contaMapper;

    @GetMapping("/{idConta}/saldo")
    public ContaDTO buscar(@PathVariable Long idConta) {
        var conta = contaService.buscar(idConta);
        var contaDto = contaMapper.toDTO(conta);
        return contaDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContaDTO salvar(@RequestBody @Valid ContaInput contaInput) {
        var conta = contaMapper.toEntity(contaInput);
        var contaDto = contaMapper.toDTO(contaService.salvar(conta));
        return contaDto;
    }

    @PutMapping("/{idConta}/bloqueio")
    public ContaDTO bloquear(@PathVariable Long idConta) {
        var conta = contaService.bloquear(idConta);
        var contaDto = contaMapper.toDTO(conta);
        return contaDto;
    }

    @PutMapping("/{idConta}/deposito")
    public ContaDTO bloquear(@PathVariable Long idConta, @RequestBody Double valor) {
        var conta = contaService.depositar(idConta, valor);
        var contaDto = contaMapper.toDTO(conta);
        return contaDto;
    }
}
