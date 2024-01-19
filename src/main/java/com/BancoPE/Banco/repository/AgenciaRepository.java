package com.BancoPE.Banco.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.BancoPE.Banco.entities.Agencia;

public interface AgenciaRepository extends JpaRepository<Agencia,Long> {

    @Query(value = "Select * from agencia where id_agencia =:id",nativeQuery = true)
    Optional<Agencia> findByAgencia(@Param("id") Long id );
    
     @Query(value = "Select * from agencia where agencia=:agencia",nativeQuery = true)
     Optional<Agencia> findByAgenciaNumero(@Param("agencia") String agencia);
} 
