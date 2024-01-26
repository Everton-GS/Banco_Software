package com.BancoPE.Banco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.BancoPE.Banco.entities.Cliente;
import com.BancoPE.Banco.repository.ClienteRepository;

@Service
public class ClienteService {

   @Autowired
   ClienteRepository clienteRepository;

   
   public Cliente registrar(@NonNull Cliente cliente){
    return clienteRepository.save(cliente);
   }

   

}
