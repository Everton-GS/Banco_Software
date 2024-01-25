package com.BancoPE.Banco.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BancoPE.Banco.entities.ClienteCartaoAuthentication;
import com.BancoPE.Banco.entities.Funcionario;
import com.BancoPE.Banco.entities.FuncionarioAuthentication;
import com.BancoPE.Banco.record.BloquearContaRecord;
import com.BancoPE.Banco.record.FuncionarioRegistrarRecord;
import com.BancoPE.Banco.repository.AgenciaRepository;
import com.BancoPE.Banco.repository.ClienteCartaoAuthenticationRepository;
import com.BancoPE.Banco.services.FuncionarioAuthenticationService;
import com.BancoPE.Banco.services.FuncionarioService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping(value = "/gerente")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @Autowired
    FuncionarioAuthenticationService funcionarioAuthenticationService;

    @Autowired
    ClienteCartaoAuthenticationRepository authenticationRepository;

    @Autowired
    AgenciaRepository agenciaRepository;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/funcionario/registrar")
    public ResponseEntity<?> registrar(@RequestBody FuncionarioRegistrarRecord funcionarioRecord) {
        try {
            Funcionario funcionarioGerente= (Funcionario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            
            if (funcionarioGerente.getAgencia()!=null) {
                Funcionario funcionario = new Funcionario(funcionarioRecord.cargo(),funcionarioGerente.getAgencia(),funcionarioRecord.cpf(),funcionarioRecord.nome(),funcionarioRecord.genero(),  funcionarioRecord.nascimento(),funcionarioRecord.endereco(),funcionarioRecord.telefone(), funcionarioRecord.email());
                funcionarioService.registrar(funcionario);
                
                FuncionarioAuthentication funcionarioAuthentication = new FuncionarioAuthentication(funcionario, funcionarioRecord.cpf(), funcionarioAuthenticationService.gerarSenha());
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
    @Transactional(rollbackOn = Exception.class)
    @PutMapping("/bloquear")
    public ResponseEntity<?> bloqueioConta(@RequestBody BloquearContaRecord bloquear ){
        try {
            ClienteCartaoAuthentication clienteCartaoAuthentication= authenticationRepository.findByCartao(bloquear.cartao());

            if(clienteCartaoAuthentication!=null){
                clienteCartaoAuthentication.setStatus(false);
                funcionarioAuthenticationService.bloquearCartao(clienteCartaoAuthentication);
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    
}
