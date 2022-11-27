package com.dockbank.bank.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dockbank.bank.domain.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findAllByContaIdConta(Long id);
}
