package com.BancoPE.Banco.entities;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "funcionario")
public class Funcionario  extends Pessoa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Long id;

    @NotNull
    @Enumerated
    @Column(name = "cargo")
    AcessoRole cargo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_agencia")
    Agencia agencia;

  
    public Funcionario(AcessoRole cargo,Agencia agencia,String cpf ,String nome, GeneroRole genero, LocalDate nascimento, String endereco, String telefone, String email) {
    super(nome,cpf ,genero, nascimento, endereco, telefone, email);
    this.cargo = cargo;
    this.agencia = agencia;
   
    }

   
}
