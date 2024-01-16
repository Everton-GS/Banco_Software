package com.BancoPE.Banco.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.BancoPE.Banco.entities.Cliente;
import com.BancoPE.Banco.services.ClienteService;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
    
    @Autowired
    ClienteService clienteService;

    @PostMapping("/registrar")
    public ResponseEntity<?>registrar(@RequestBody Cliente cliente){
        try {
            if(cliente==null){
                ResponseEntity.badRequest().build();
            }
            clienteService.registrar(cliente);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }



}
