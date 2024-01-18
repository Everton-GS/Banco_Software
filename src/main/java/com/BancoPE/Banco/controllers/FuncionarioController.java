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
import com.BancoPE.Banco.record.FuncionarioRegistrarRecord;
import com.BancoPE.Banco.repository.AgenciaRepository;
import com.BancoPE.Banco.services.FuncionarioService;

@RestController
@RequestMapping(value = "/gerente")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @Autowired
    AgenciaRepository agenciaRepository;

    @PostMapping("/registrar/funcionario")
    public ResponseEntity<?> registrar(@RequestBody FuncionarioRegistrarRecord funcionarioRecord) {
        try {
           // Funcionario gerente = (Funcionario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Optional<Agencia> agencia = agenciaRepository.findByAgencia(funcionarioRecord.agencia());

            Agencia agencia2= agencia.get();

            if (funcionarioRecord.login() == null || agencia.isPresent()!=true) {
                return ResponseEntity.badRequest().build();
            } else {
                Funcionario funcionario = new Funcionario(funcionarioRecord.cargRole(),
                        agencia2,
                        funcionarioRecord.nome(),
                        funcionarioRecord.genero(),
                        funcionarioRecord.cpf(),
                        funcionarioRecord.nascimento(),
                        funcionarioRecord.endereco(),
                        funcionarioRecord.telefone(),
                        funcionarioRecord.email());
                funcionarioService.registrar(funcionario);

                return ResponseEntity.ok().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
