package com.BancoPE.Banco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BancoPE.Banco.entities.ExtratoCartao;
import com.BancoPE.Banco.repository.ExtratoCartaoRepository;
import lombok.NonNull;

@Service
public class ExtratoCartaoService {

    @Autowired
    ExtratoCartaoRepository cartaoRepository;
    
    public void registrar(@NonNull ExtratoCartao extratoCartao ){
            cartaoRepository.save(extratoCartao);
    }
}
