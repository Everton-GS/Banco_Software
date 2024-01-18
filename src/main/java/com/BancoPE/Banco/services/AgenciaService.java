package com.BancoPE.Banco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.BancoPE.Banco.entities.Agencia;
import com.BancoPE.Banco.repository.AgenciaRepository;

@Service
public class AgenciaService {
    
     @Autowired
     AgenciaRepository agenciaRepository;


     public void adicionar(@NonNull Agencia agencia){
        agenciaRepository.save(agencia);
     }

    
}
