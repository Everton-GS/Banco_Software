package com.BancoPE.Banco.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BancoPE.Banco.entities.Funcionario;
import com.BancoPE.Banco.services.FuncionarioService;

@RestController
@RequestMapping(value = "/gerente")
public class FuncionarioController {


    @Autowired
    FuncionarioService funcionarioService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody Funcionario funcionario){
        try {
            if(funcionario!=null){
                funcionarioService.registrar(funcionario);
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
                return ResponseEntity.internalServerError().build();
        }
    }
}
