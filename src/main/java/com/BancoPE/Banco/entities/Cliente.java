package com.BancoPE.Banco.entities;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente extends Pessoa{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @Column(name = "cargo")
    AcessoRole cargo;

    public Cliente(String nome,String cpf,GeneroRole genero,LocalDate nascimento,String endereco,String telefone,String email,AcessoRole cargo){
        super(nome,cpf,genero,nascimento,endereco,telefone,email);
        this.cargo=cargo;
        
    }
    
}
