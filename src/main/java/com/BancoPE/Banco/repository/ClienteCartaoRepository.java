package com.BancoPE.Banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BancoPE.Banco.entities.ClienteCartao;

public interface ClienteCartaoRepository extends JpaRepository<ClienteCartao,Long> {
    
}
