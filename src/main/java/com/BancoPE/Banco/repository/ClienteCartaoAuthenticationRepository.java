package com.BancoPE.Banco.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.BancoPE.Banco.entities.ClienteCartaoAuthentication;

public interface ClienteCartaoAuthenticationRepository extends JpaRepository<ClienteCartaoAuthentication,Long> {
     

     @Query(value = "select * from cartao where cartao =:cartao",nativeQuery = true)
     ClienteCartaoAuthentication findByCartao(String cartao);


     @Query(value = "SELECT * FROM cartao WHERE id_cartao = (SELECT MAX(id_cartao) FROM cartao)", nativeQuery = true)
     Optional<ClienteCartaoAuthentication> findByUltimoCartao();

}
