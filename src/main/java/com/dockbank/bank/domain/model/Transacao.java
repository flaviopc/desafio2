package com.dockbank.bank.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransacao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idConta", referencedColumnName = "idConta")
    private Conta conta;

    @NotNull
    private double valor;

    @NotNull
    private String tipoTransacao;

    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime dataTransacao;
}
