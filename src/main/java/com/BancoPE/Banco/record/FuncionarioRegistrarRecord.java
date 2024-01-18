package com.BancoPE.Banco.record;

import java.time.LocalDate;
import com.BancoPE.Banco.entities.AcessoRole;

public record FuncionarioRegistrarRecord(
        String login,
        String senha,
        String nome,
        String cpf,
        AcessoRole cargRole,
        Long agencia,
        String genero,
        LocalDate nascimento,
        String endereco,
        String telefone,
        String email
) {
    
}
