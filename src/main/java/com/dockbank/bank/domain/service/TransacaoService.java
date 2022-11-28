package com.dockbank.bank.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dockbank.bank.domain.model.Conta;
import com.dockbank.bank.domain.model.Transacao;
import com.dockbank.bank.domain.repository.TransacaoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransacaoService {
    private TransacaoRepository repository;

    public void salvar(Conta conta, double valor, String tipoTransacao) {
        Transacao transacao = new Transacao();
        transacao.setConta(conta);
        transacao.setValor(valor);
        transacao.setTipoTransacao(tipoTransacao);
        transacao.setDataTransacao(LocalDateTime.now());
        repository.save(transacao);
    }

    public List<Transacao> mostrarTodas() {
        return repository.findAll();
    }

    public List<Transacao> mostrarTodasDeUmaConta(Long idConta) {
        return repository.findAllByContaIdConta(idConta);
    }

    public List<Transacao> mostrarPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return repository.findAllByDataTransacaoBetween(dataInicial, dataFinal);
    }

}
