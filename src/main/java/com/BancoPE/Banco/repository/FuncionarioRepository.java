package com.BancoPE.Banco.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.BancoPE.Banco.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {
    

     @Query(value = "Select * from funcionario where login=:login",nativeQuery = true)
     Funcionario findByNome(String login);
}
