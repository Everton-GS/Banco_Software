package com.BancoPE.Banco.entities;

import java.time.LocalDate;
import org.hibernate.validator.constraints.br.CPF;
import com.BancoPE.Banco.validation.constraints.Telefone;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
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
    
    @NotBlank(message = "Nome em branco")
    @Column(name = "nome")
    private String nome;

    @NotBlank(message = "cpf em branco")
    @CPF
    @Column(name = "cpf")
    private String cpf;

    @NotNull
    @Column(name = "genero")
    GeneroRole generoRole;

   
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "UTC")
    @Column(name = "nascimento")
    private LocalDate nascimento;

    @NotBlank(message = "endereço em branco")
    @Column(name = "endereco")
    private String endereco;

    @NotBlank
    @Telefone(message = "telefone inválido")
    @Column(name = "telefone")
    private String telefone;
    
    @NotBlank
    @Email
    @Column(name = "email")
    private String email;
}
