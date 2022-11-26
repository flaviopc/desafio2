package com.dockbank.bank.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dockbank.bank.domain.model.Conta;
import com.dockbank.bank.domain.service.ContaService;

@RequestMapping("/contas")
@RestController
public class ContaController {
    @Autowired
    private ContaService contaService;

    @GetMapping("/{idConta}/saldo")
    public double buscar(@PathVariable Long idConta) {
        return contaService.saldo(idConta);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Conta salvar(@RequestBody Conta conta) {
        return contaService.salvar(conta);
    }

    @PutMapping("/{idConta}/bloqueio")
    public Conta bloquear(@PathVariable Long idConta) {
        return contaService.bloquear(idConta);
    }

    @PutMapping("/{idConta}/deposito")
    public Conta bloquear(@PathVariable Long idConta, @RequestBody Double valor) {
        return contaService.depositar(idConta, valor);
    }
}
