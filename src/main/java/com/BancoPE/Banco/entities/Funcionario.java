package com.BancoPE.Banco.entities;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @Column(name = "id_funcionario")
    private Long id;

    @Column(name = "cargo")
    FuncionarioRole cargo;

    @ManyToOne
    @JoinColumn(name = "id_agencia")
    Agencia agencia;

    @Column(name = "login")
    private String login;

    @Column(name = "senha")
    private String senha;

    public Funcionario(Long id,FuncionarioRole cargo,String login,String senha,Agencia agencia,String nome,String genero,LocalDate nascimento,String endereco,String telefone,String email){
     super(nome,genero,nascimento,endereco,telefone,email);
     this.id=id;
     this.cargo=cargo;
     this.agencia=agencia;
     this.login=login;
     this.senha=senha;
    }
}
