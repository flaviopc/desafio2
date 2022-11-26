package com.dockbank.bank.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dockbank.bank.domain.exception.ContaNaoEncontradaException;
import com.dockbank.bank.domain.exception.SaldoInsuficienteException;
import com.dockbank.bank.domain.model.Conta;
import com.dockbank.bank.domain.repository.ContaRepository;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    public Conta salvar(Conta conta) {
        conta.ativarConta();
        conta.setDataCriacao(LocalDateTime.now());
        return contaRepository.save(conta);
    }

    public Conta buscar(Long idConta) {
        return contaRepository.findById(idConta).orElseThrow(() -> new ContaNaoEncontradaException(idConta));
    }

    public double saldo(Long idConta) {
        var conta = buscar(idConta);
        return conta.getSaldo();
    }

    public Conta bloquear(Long idConta) {
        var conta = buscar(idConta);
        conta.bloquearConta();
        return contaRepository.save(conta);
    }

    public Conta depositar(Long idConta, double valor) {
        var conta = buscar(idConta);
        conta.setSaldo(conta.getSaldo() + valor);
        salvar(conta);
        return conta;
    }

    public Conta sacar(Long idConta, double valor) {
        var conta = buscar(idConta);
        if (conta.getSaldo() >= valor)
            conta.setSaldo(conta.getSaldo() - valor);
        else
            throw new SaldoInsuficienteException("Sua conta não possui saldo suficiente");
        return contaRepository.save(conta);
    }

}
