package com.BancoPE.Banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.BancoPE.Banco.entities.FuncionarioAuthentication;

public interface FuncionarioAuthenticationRepository extends JpaRepository<FuncionarioAuthentication,Long> {
    

    @Query(name = "Select * from funcionarioAcesso where login =:login")
    FuncionarioAuthentication findByLogin(@Param("login")String login);
}
