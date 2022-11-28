package com.dockbank.bank.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransacaoServiceTest {
    @Autowired
    private TransacaoService transacaoService;
    @Autowired
    private ContaService contaService;

    @Test
    public void deveCadastrarUmaTransacaoComSucesso() {
        var conta = contaService.buscar(1L);
        int valor = 100;
        var transacaoSalva = transacaoService.salvar(conta, valor, "DEPOSITO");

        assertNotNull(transacaoSalva);
        assertEquals(valor, transacaoSalva.getValor());
    }

    @Test
    public void deveRetornarAQuantidadeDeTransacoesCorretaDaConta() {
        var conta = contaService.buscar(2L);
        var transacoes = transacaoService.mostrarTodasDeUmaConta(conta.getIdConta());

        assertEquals(0, transacoes.size());

        int valor = 100;
        transacaoService.salvar(conta, valor, "DEPOSITO");
        transacoes = transacaoService.mostrarTodasDeUmaConta(conta.getIdConta());

        assertEquals(1, transacoes.size());
    }

    @Test
    public void deveRetornarAQuantidadeDeTransacoesNoPeriodo() {
        LocalDateTime dataInicial = LocalDateTime.of(LocalDate.of(2022, 5, 10), LocalTime.of(0,
                0, 0));

        LocalDateTime dataFinal = LocalDateTime.of(LocalDate.of(2022, 7, 9), LocalTime.of(23, 59, 59));

        var transacoes = transacaoService.mostrarPorPeriodo(dataInicial, dataFinal);

        assertEquals(3, transacoes.size());
    }
}
