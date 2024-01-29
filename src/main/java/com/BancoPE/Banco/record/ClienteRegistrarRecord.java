package com.BancoPE.Banco.record;

import java.time.LocalDate;

import com.BancoPE.Banco.entities.AcessoRole;
import com.BancoPE.Banco.entities.ClienteCartaoAuthentication;
import com.BancoPE.Banco.entities.GeneroRole;

public record ClienteRegistrarRecord(
        String nome,
        String cpf,
        AcessoRole cargo,
        ClienteCartaoAuthentication cartao,
        GeneroRole genero,
        LocalDate nascimento,
        String endereco,
        String telefone,
        String email
) {
    
}
