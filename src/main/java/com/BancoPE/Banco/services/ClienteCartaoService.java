package com.BancoPE.Banco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BancoPE.Banco.entities.ClienteCartaoAuthentication;
import com.BancoPE.Banco.repository.ClienteCartaoAuthenticationRepository;

import lombok.NonNull;

@Service
public class ClienteCartaoService {
    
    @Autowired
    ClienteCartaoAuthenticationRepository clienteCartaoAuthenticationRepository;

    public void registrar (@NonNull ClienteCartaoAuthentication clienteCartaoAuthentication){
        this.clienteCartaoAuthenticationRepository.save(clienteCartaoAuthentication);
    }
}
