package com.BancoPE.Banco.record;

import java.time.LocalDate;
import com.BancoPE.Banco.entities.AcessoRole;
import com.BancoPE.Banco.entities.GeneroRole;

import jakarta.validation.constraints.NotNull;

public record FuncionarioRegistrarRecord(
        @NotNull String nome,
        @NotNull String cpf,
        AcessoRole cargo,
        String agencia,
        GeneroRole genero,
        LocalDate nascimento,
        @NotNull String endereco,
        @NotNull String telefone,
        @NotNull String email
) {
    
}
