package com.dockbank.bank.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dockbank.bank.domain.exception.ContaNaoEncontradaException;
import com.dockbank.bank.domain.exception.SaldoInsuficienteException;
import com.dockbank.bank.domain.model.Conta;
import com.dockbank.bank.domain.model.Pessoa;

@SpringBootTest
public class ContaServiceTest {
    @Autowired
    private ContaService contaService;

    @Test
    public void deveCadastrarUmaContaComSucesso() {
        var pessoa = new Pessoa();
        pessoa.setIdPessoa(1L);

        var conta = new Conta();
        conta.setPessoa(pessoa);
        var contaSalva = contaService.salvar(conta);

        assertNotNull(contaSalva);
        assertEquals(4, contaSalva.getIdConta());
    }

    @Test
    public void deveAumentarOSaldoDaContaAoFazerDeposito() {
        var pessoa = new Pessoa();
        pessoa.setIdPessoa(1L);

        var conta = new Conta();
        conta.setPessoa(pessoa);
        var contaSalva = contaService.salvar(conta);

        double valor = 100;
        contaSalva = contaService.depositar(contaSalva.getIdConta(), valor);
        var contaComDeposito = conta.getSaldo() + valor;

        assertEquals(contaComDeposito, contaSalva.getSaldo());
    }

    @Test
    public void deveDiminuirOSaldoDaContaAoFazerSaque() {
        var pessoa = new Pessoa();
        pessoa.setIdPessoa(1L);

        var conta = new Conta();
        conta.setPessoa(pessoa);
        conta.setSaldo(100);
        var contaSalva = contaService.salvar(conta);

        double valor = 50;
        contaSalva = contaService.sacar(contaSalva.getIdConta(), valor);
        var saldoMenosSaque = conta.getSaldo() - valor;

        assertEquals(saldoMenosSaque, contaSalva.getSaldo());
    }

    @Test
    public void deveFalharAoFazerSaqueSemSaldoSuficiente() {
        var pessoa = new Pessoa();
        pessoa.setIdPessoa(1L);

        var conta = new Conta();
        conta.setPessoa(pessoa);
        var contaSalva = contaService.salvar(conta);
        final Long idConta = contaSalva.getIdConta();
        double valor = 100;
        assertThrows(SaldoInsuficienteException.class, () -> contaService.sacar(idConta, valor));
    }

    @Test
    public void deveFalharQuandoNaoEncontrarAConta() {
        assertThrows(ContaNaoEncontradaException.class, () -> contaService.buscar(15L));
    }
}
