package com.BancoPE.Banco.entities;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Pessoa {
    
    @Column(name = "nome")
    private String nome;

    @Column(name = "genero")
    private String genero;

    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "UTC")
    @Column(name = "nascimento")
    private LocalDate nascimento;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "telefone")
    private String telefone;
    
    @Column(name = "email")
    private String email;
}
