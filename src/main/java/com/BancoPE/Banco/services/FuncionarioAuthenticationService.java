package com.BancoPE.Banco.services;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.BancoPE.Banco.entities.FuncionarioAuthentication;
import com.BancoPE.Banco.repository.FuncionarioAuthenticationRepository;

import lombok.NonNull;

@Service
public class FuncionarioAuthenticationService {
    
    @Autowired
    FuncionarioAuthenticationRepository funcionarioAuthenticationRepository;



    public FuncionarioAuthentication registrar(@NonNull FuncionarioAuthentication funcionarioAuthentication){
       return funcionarioAuthenticationRepository.save(funcionarioAuthentication);
    }

    public String gerarSenha(){

        int tamanho=10;
        String exemplo="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder senha = new StringBuilder();
        
        for(int i=0;i<tamanho;i++){
            int index=secureRandom.nextInt(exemplo.length());
            senha.append(exemplo.charAt(index));
        }
        String novaSenha = senha.toString();
        return novaSenha;
    }
}
