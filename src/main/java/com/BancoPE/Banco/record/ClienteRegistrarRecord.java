package com.BancoPE.Banco.record;

import java.time.LocalDate;

import com.BancoPE.Banco.entities.AcessoRole;
import com.BancoPE.Banco.entities.ClienteCartaoAuthentication;

public record ClienteRegistrarRecord(
        String login,
        String senha,
        String nome,
        AcessoRole cargRole,
        ClienteCartaoAuthentication cartao,
        String genero,
        LocalDate nascimento,
        String endereco,
        String telefone,
        String email
) {
    
}
