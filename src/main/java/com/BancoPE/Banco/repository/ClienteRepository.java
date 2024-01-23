package com.BancoPE.Banco.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.BancoPE.Banco.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long>{
    

   @Query(value = "Select * from cliente where cpf=:cpf",nativeQuery = true)
   Optional<Cliente> findByCpf(@Param("cpf")String cpf);


    
}
