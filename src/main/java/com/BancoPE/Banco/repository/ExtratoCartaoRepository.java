package com.BancoPE.Banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BancoPE.Banco.entities.ExtratoCartao;

public interface ExtratoCartaoRepository extends JpaRepository<ExtratoCartao,Long> {
    
}
