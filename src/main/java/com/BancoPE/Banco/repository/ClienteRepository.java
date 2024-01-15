package com.BancoPE.Banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.BancoPE.Banco.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long>{
    

    @Query(value = "select * from cliente where numero =:numero",nativeQuery = true)
    Cliente findByCartao(String numero);
}
