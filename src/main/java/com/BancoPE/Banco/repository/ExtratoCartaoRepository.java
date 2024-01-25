package com.BancoPE.Banco.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.BancoPE.Banco.entities.ExtratoCartao;

public interface ExtratoCartaoRepository extends JpaRepository<ExtratoCartao,Long> {
    
    @Query(value = "select * from extrato where id = :id", nativeQuery = true)
    Optional<ExtratoCartao> findByID(@Param("id") long id);
 


}
