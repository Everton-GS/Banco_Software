package com.BancoPE.Banco.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.BancoPE.Banco.entities.Agencia;
import com.BancoPE.Banco.entities.Funcionario;
import com.BancoPE.Banco.entities.FuncionarioAuthentication;
import com.BancoPE.Banco.record.FuncionarioRegistrarRecord;
import com.BancoPE.Banco.repository.AgenciaRepository;
import com.BancoPE.Banco.services.FuncionarioAuthenticationService;
import com.BancoPE.Banco.services.FuncionarioService;

@RestController
@RequestMapping(value = "/gerente")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @Autowired
    FuncionarioAuthenticationService funcionarioAuthenticationService;

    @Autowired
    AgenciaRepository agenciaRepository;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody FuncionarioRegistrarRecord funcionarioRecord) {
        try {
            // Funcionario gerente = (Funcionario)
            // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println("oi");
            Optional<Agencia> agencia = agenciaRepository.findByAgenciaNumero(funcionarioRecord.agencia());

            Agencia agencia2 = agencia.get();
            if (agencia.isPresent()) {
                Funcionario funcionario = new Funcionario(funcionarioRecord.cargo(),agencia2,funcionarioRecord.cpf(),funcionarioRecord.nome(),funcionarioRecord.genero(),  funcionarioRecord.nascimento(),funcionarioRecord.endereco(),funcionarioRecord.telefone(), funcionarioRecord.email());
                funcionarioService.registrar(funcionario);
                
                FuncionarioAuthentication funcionarioAuthentication = new FuncionarioAuthentication(funcionario, funcionarioRecord.cpf(), funcionarioAuthenticationService.gerarSenha());
                
                System.out.println("eeee");
                funcionarioAuthenticationService.registrar(funcionarioAuthentication);
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
