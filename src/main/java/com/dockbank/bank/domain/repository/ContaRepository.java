package com.dockbank.bank.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dockbank.bank.domain.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}
