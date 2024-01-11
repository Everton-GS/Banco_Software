package com.BancoPE.Banco.entities;

import java.time.LocalDateTime;



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
@Table(name = "funcionario")
public class Funcionario extends Pessoa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cargo")
    FuncionarioRole cargo;

    public Funcionario(Long id,FuncionarioRole cargo,String nome,String genero,LocalDateTime nascimento,String endereco,String telefone,String email){
     super(nome,genero,nascimento,endereco,telefone,email);
     this.id=id;
     this.cargo=cargo;
    }
}
