package com.BancoPE.Banco.record;

import java.time.LocalDate;
import com.BancoPE.Banco.entities.AcessoRole;
import com.BancoPE.Banco.entities.GeneroRole;

public record FuncionarioRegistrarRecord(
        String nome,
        String cpf,
        AcessoRole cargo,
        String agencia,
        GeneroRole genero,
        LocalDate nascimento,
        String endereco,
        String telefone,
        String email
) {
    
}
