package com.BancoPE.Banco.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.BancoPE.Banco.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {
    

     @Query(value = "Select * from funcionario where cpf=:cpf",nativeQuery = true)
     Funcionario findByNome(@Param("cpf") String cpf);
}
