package com.dockbank.bank.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dockbank.bank.domain.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

}
