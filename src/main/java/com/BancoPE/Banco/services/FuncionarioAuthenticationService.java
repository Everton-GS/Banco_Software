package com.BancoPE.Banco.services;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BancoPE.Banco.entities.ClienteCartaoAuthentication;
import com.BancoPE.Banco.entities.FuncionarioAuthentication;
import com.BancoPE.Banco.repository.ClienteCartaoAuthenticationRepository;
import com.BancoPE.Banco.repository.FuncionarioAuthenticationRepository;
import lombok.NonNull; 

@Service
public class FuncionarioAuthenticationService {

    @Autowired
    FuncionarioAuthenticationRepository funcionarioAuthenticationRepository;

    @Autowired
    ClienteCartaoAuthenticationRepository authenticationRepository;

    public void registrar(@NonNull FuncionarioAuthentication funcionarioAuthentication){
        funcionarioAuthenticationRepository.save(funcionarioAuthentication);
    }

    public String gerarSenha(){
        String senha="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        int tamanho=10;
        StringBuilder senhaNova = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for(int i=0;i<tamanho;i++){
            int index= secureRandom.nextInt(senha.length());
            senhaNova.append(senha.charAt(index));

        }
        return senhaNova.toString();

    }
    public void bloquearCartao(ClienteCartaoAuthentication clienteCartao){
            clienteCartao.setStatus(false);
            authenticationRepository.save(clienteCartao);

    }
}
