package com.BancoPE.Banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.BancoPE.Banco.entities.ClienteCartaoAuthentication;

public interface ClienteCartaoRepository extends JpaRepository<ClienteCartaoAuthentication,Long> {
    

     @Query(value = "select * from cartao where cartao =:numero",nativeQuery = true)
     ClienteCartaoAuthentication findByCartao(String numero);
}
