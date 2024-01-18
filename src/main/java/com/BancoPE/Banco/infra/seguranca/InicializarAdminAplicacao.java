package com.BancoPE.Banco.infra.seguranca;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.BancoPE.Banco.entities.AcessoRole;
import com.BancoPE.Banco.entities.Agencia;
import com.BancoPE.Banco.entities.Funcionario;
import com.BancoPE.Banco.entities.GeneroRole;
import com.BancoPE.Banco.repository.AgenciaRepository;
import com.BancoPE.Banco.repository.FuncionarioRepository;

@Component
public class InicializarAdminAplicacao implements CommandLineRunner {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    AgenciaRepository agenciaRepository;

    @Override
    public void run(String... args) throws Exception {
        cadastrarAgencia();
        cadastrarFuncionarioMaster();
    }
    private void cadastrarAgencia() {
        Optional<Agencia> agencia = agenciaRepository.findByAgencia("0001");
        if (agencia.isEmpty()) {
            Agencia agenciaCriar = new Agencia("0001", "Rua do Sol", "3897-3978");
            agenciaRepository.save(agenciaCriar);
        }
    }
    private void cadastrarFuncionarioMaster(){
        Optional<Agencia> agencia = agenciaRepository.findByAgencia("0001");
        if (funcionarioRepository.findByNome("Gerente_Master") == null && agencia.isPresent()) {
            Funcionario funcionarioGerente = new Funcionario(AcessoRole.Gerente,agencia.get(),"784.475.123-31",
                    "Everton", GeneroRole.Masculino, LocalDate.of(2000, 04, 10), "Rua do sol", "4002-8922", "Banco@gmail.com");
            funcionarioRepository.save(funcionarioGerente);
        }
    }

}
