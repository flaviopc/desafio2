package com.dockbank.bank.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
public class Conta {

    private static final String ATIVA = "ATIVA";
    private static final String BLOQUEADA = "BLOQUEADA";
    public static final int CONTA_ATIVA = 1;
    public static final int CONTA_INATIVA = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConta;

    @NotNull
    @OneToOne
    @JoinColumn(name = "idPessoa", referencedColumnName = "idPessoa")
    private Pessoa pessoa;
    private double saldo;
    private double limiteSaqueDiario;
    private int flagAtivo;
    private int tipoConta;
    private String status;

    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime dataCriacao;

    public void bloquearConta() {
        this.flagAtivo = CONTA_INATIVA;
        status = BLOQUEADA;
    }

    public void ativarConta() {
        this.flagAtivo = CONTA_ATIVA;
        status = ATIVA;
    }

    public boolean estaAtiva() {
        return getFlagAtivo() == Conta.CONTA_ATIVA;
    }
}