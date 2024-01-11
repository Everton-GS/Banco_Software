package com.BancoPE.Banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BancoPE.Banco.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {
    
}
