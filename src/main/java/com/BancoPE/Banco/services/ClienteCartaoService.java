package com.BancoPE.Banco.services;

import java.security.SecureRandom;

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

    public String gerarSenha(){
        String senha="0123456789";
        int tamanho=5;
        StringBuilder senhaNova = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for(int i=0;i<=tamanho;i++){
            int index= secureRandom.nextInt(senha.length());
            senhaNova.append(senha.charAt(index));
        }
        return senhaNova.toString();
    }

    public void transferenciaValor(Double valor,ClienteCartaoAuthentication remetente,ClienteCartaoAuthentication destinatario){
        if(remetente.getSaldo()>=valor){
            Double valorAtualizarRemetente=remetente.getSaldo()-valor;
            Double valorAtualizarDestinatario=destinatario.getSaldo()+valor;

            remetente.setSaldo(valorAtualizarRemetente);
            clienteCartaoAuthenticationRepository.save(remetente);

            destinatario.setSaldo(valorAtualizarDestinatario);
            clienteCartaoAuthenticationRepository.save(destinatario);

        }

    }
}
