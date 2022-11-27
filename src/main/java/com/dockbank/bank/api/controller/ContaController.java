package com.dockbank.bank.api.controller;

import java.util.List;

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

import com.dockbank.bank.commom.modelmapper.ContaMapper;
import com.dockbank.bank.commom.modelmapper.TransacaoMapper;
import com.dockbank.bank.domain.dto.ContaDTO;
import com.dockbank.bank.domain.dto.TransacaoDTO;
import com.dockbank.bank.domain.dto.input.ContaInput;
import com.dockbank.bank.domain.dto.input.DepositoInput;
import com.dockbank.bank.domain.dto.input.SaqueInput;
import com.dockbank.bank.domain.dto.input.TransacoesContaDTO;
import com.dockbank.bank.domain.model.Transacao;
import com.dockbank.bank.domain.service.ContaService;
import com.dockbank.bank.domain.service.TransacaoService;

import lombok.AllArgsConstructor;

@RequestMapping("/contas")
@RestController
@AllArgsConstructor
public class ContaController {
    private ContaService contaService;
    private TransacaoService transacaoService;
    private ContaMapper contaMapper;
    private TransacaoMapper transacaoMapper;

    @GetMapping("/{idConta}/saldo")
    public ContaDTO saldo(@PathVariable Long idConta) {
        var conta = contaService.buscar(idConta);
        return contaMapper.toDTO(conta);
    }

    @GetMapping("/{idConta}/extrato")
    public TransacoesContaDTO extrato(@PathVariable Long idConta) {
        var conta = contaService.buscar(idConta);
        List<Transacao> transacoes = transacaoService.mostrarTodasDeUmaConta(conta.getIdConta());
        List<TransacaoDTO> transacoesDTO = transacaoMapper.toListDTO(transacoes);
        TransacoesContaDTO transacoesContaDTO = new TransacoesContaDTO();
        transacoesContaDTO.setConta(contaMapper.toDTO(conta));
        transacoesContaDTO.setTransacoes(transacoesDTO);
        return transacoesContaDTO;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContaDTO salvar(@RequestBody @Valid ContaInput contaInput) {
        var conta = contaMapper.toEntity(contaInput);
        var contaSalva = contaService.salvar(conta);
        return contaMapper.toDTO(contaSalva);
    }

    @PutMapping("/{idConta}/bloqueio")
    public ContaDTO bloquear(@PathVariable Long idConta) {
        var conta = contaService.bloquear(idConta);
        return contaMapper.toDTO(conta);
    }

    @PutMapping("/{idConta}/saque")
    public ContaDTO saque(@PathVariable Long idConta, @RequestBody @Valid SaqueInput saque) {
        var valor = saque.getValor();
        var conta = contaService.sacar(idConta, valor);
        return contaMapper.toDTO(conta);
    }

    @PutMapping("/{idConta}/deposito")
    public ContaDTO deposito(@PathVariable Long idConta, @RequestBody @Valid DepositoInput deposito) {
        var valor = deposito.getValor();
        var conta = contaService.depositar(idConta, valor);
        return contaMapper.toDTO(conta);
    }
}
