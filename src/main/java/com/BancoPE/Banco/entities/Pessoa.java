package com.BancoPE.Banco.entities;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    
    @NotBlank
    @Column(name = "nome")
    private String nome;

    @NotBlank
    @Column(name = "cpf")
    private String cpf;

    @NotNull
    @Column(name = "genero")
    GeneroRole generoRole;

    @NotBlank
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "UTC")
    @Column(name = "nascimento")
    private LocalDate nascimento;

    @NotBlank
    @Column(name = "endereco")
    private String endereco;

    @NotBlank
    @Column(name = "telefone")
    private String telefone;
    
    @NotBlank
    @Column(name = "email")
    private String email;
}
