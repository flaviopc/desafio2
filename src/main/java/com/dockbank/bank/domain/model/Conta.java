package com.dockbank.bank.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConta;
    private Long idPessoa;
    private double saldo;
    private double limiteSaqueDiario;
    private int flagAtivo;
    private int tipoConta;
    private LocalDateTime dataCriacao;
}