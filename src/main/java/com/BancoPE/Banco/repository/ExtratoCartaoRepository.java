package com.BancoPE.Banco.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.BancoPE.Banco.entities.ExtratoCartao;

public interface ExtratoCartaoRepository extends JpaRepository<ExtratoCartao,Long> {
    
    @Query(value = "select * from extrato where id = :id", nativeQuery = true)
    Optional<ExtratoCartao> findByID(@Param("id") long id);
 

    @Query(value = "SELECT * FROM extrato WHERE destinatario_id = :id or remetente_id = :id", nativeQuery = true)
    List<ExtratoCartao> findByall(@Param("id") Long id);

}
