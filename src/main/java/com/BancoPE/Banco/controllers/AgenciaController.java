package com.BancoPE.Banco.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.BancoPE.Banco.entities.Agencia;
import com.BancoPE.Banco.record.AgenciaCadastrar;
import com.BancoPE.Banco.repository.AgenciaRepository;
import com.BancoPE.Banco.services.AgenciaService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/agencia")
public class AgenciaController {

    @Autowired
    AgenciaService agenciaService;

    @Autowired
    AgenciaRepository agenciaRepository;


    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/cadastrar")
    public ResponseEntity<?> criar(@RequestBody AgenciaCadastrar agenciaCadastrar){
       try {
            Optional<Agencia> agencia = agenciaRepository.findByAgenciaNumero(agenciaCadastrar.numero());
            if(agencia.isEmpty()){
                Agencia agenciaSalvar = new Agencia(agenciaCadastrar.numero(), agenciaCadastrar.endereco(), agenciaCadastrar.contato());
                agenciaService.adicionar(agenciaSalvar);
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.badRequest().build();
            }
       } catch (Exception e) {
         return ResponseEntity.internalServerError().build();
       }
    }
}
