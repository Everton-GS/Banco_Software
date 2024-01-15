package com.BancoPE.Banco.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BancoPE.Banco.entities.Agencia;
import com.BancoPE.Banco.services.AgenciaService;

@RestController
@RequestMapping("/agencia")
public class AgenciaController {

    @Autowired
    AgenciaService agenciaService;


    @PostMapping("/cadastrar")
    public ResponseEntity<Agencia> criar(@RequestBody Agencia agencia){
        try {
            if(agencia!=null){
                agenciaService.adicionar(agencia);
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
