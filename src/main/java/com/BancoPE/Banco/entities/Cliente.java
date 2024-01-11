package com.BancoPE.Banco.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente extends Pessoa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Cliente(Long id,String nome,String genero,LocalDateTime nascimento,String endereco,String telefone,String email){
        super(nome,genero,nascimento,endereco,telefone,email);
        this.id=id;
        
    }
}
