package com.BancoPE.Banco.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.BancoPE.Banco.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {
    

     @Query(value = "Select * from funcionario where cpf=:cpf",nativeQuery = true)
     Optional<Funcionario> findBycpf(@Param("cpf") String cpf);
}
