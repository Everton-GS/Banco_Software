package com.BancoPE.Banco.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import com.BancoPE.Banco.entities.Funcionario;
import com.BancoPE.Banco.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
    

    @Autowired
    FuncionarioRepository funcionarioRepository;

    public Funcionario registrar(@NonNull Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }

    
}
