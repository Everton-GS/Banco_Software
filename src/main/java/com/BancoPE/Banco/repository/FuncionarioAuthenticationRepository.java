package com.BancoPE.Banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.BancoPE.Banco.entities.FuncionarioAuthentication;

public interface FuncionarioAuthenticationRepository extends JpaRepository<FuncionarioAuthentication,Long> {
    

    @Query(value = "Select * from funcionario_acesso where login =:login",nativeQuery = true)
    FuncionarioAuthentication findByLogin(@Param("login")String login);
}
