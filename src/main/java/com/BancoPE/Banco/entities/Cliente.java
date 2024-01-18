package com.BancoPE.Banco.entities;

import java.time.LocalDate;
import jakarta.persistence.Column;
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
public class Cliente extends Pessoa{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @Column(name = "cargo")
    AcessoRole cargo;

    public Cliente(Long id,String nome,String cpf,String genero,LocalDate nascimento,String endereco,String telefone,String email){
        super(nome,cpf,genero,nascimento,endereco,telefone,email);
        this.id=id;
        
    }
    
   

    
}
