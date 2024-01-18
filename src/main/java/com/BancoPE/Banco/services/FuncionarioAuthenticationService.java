package com.BancoPE.Banco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.BancoPE.Banco.entities.FuncionarioAuthentication;
import com.BancoPE.Banco.repository.FuncionarioAuthenticationRepository;

@Service
public class FuncionarioAuthenticationService {
    
    @Autowired
    FuncionarioAuthenticationRepository funcionarioAuthenticationRepository;



    public void registrar(@NonNull FuncionarioAuthentication funcionarioAuthentication){
        funcionarioAuthenticationRepository.save(funcionarioAuthentication);
    }
}
