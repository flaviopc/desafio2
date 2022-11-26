package com.dockbank.bank.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Conta {
    public static final int CONTA_ATIVA = 1;
    public static final int CONTA_INATIVA = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConta;
    @OneToOne
    @JoinColumn(name = "idPessoa", referencedColumnName = "idPessoa")
    private Pessoa pessoa;
    private double saldo;
    private double limiteSaqueDiario;
    private int flagAtivo;
    private int tipoConta;
    private LocalDateTime dataCriacao;

    public void bloquearConta() {
        this.flagAtivo = CONTA_INATIVA;
    }

    public void ativarConta() {
        this.flagAtivo = CONTA_ATIVA;
    }

    public boolean estaAtiva() {
        return getFlagAtivo() == Conta.CONTA_ATIVA;
    }
}