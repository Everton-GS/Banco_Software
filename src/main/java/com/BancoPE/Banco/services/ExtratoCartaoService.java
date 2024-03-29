package com.BancoPE.Banco.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.BancoPE.Banco.entities.ClienteCartaoAuthentication;
import com.BancoPE.Banco.entities.ExtratoCartao;
import com.BancoPE.Banco.entities.StatusTransferenciaRole;
import com.BancoPE.Banco.repository.ClienteCartaoAuthenticationRepository;
import com.BancoPE.Banco.repository.ExtratoCartaoRepository;
import lombok.NonNull;

@Service
public class ExtratoCartaoService {

    @Autowired
    ExtratoCartaoRepository cartaoRepository;

    @Autowired
    ClienteCartaoAuthenticationRepository clienteCartaoAuthentication;

    public void registrar(@NonNull ExtratoCartao extratoCartao) {
        cartaoRepository.save(extratoCartao);
    }

    public void estornarValor(@NonNull ExtratoCartao extratoCartao, ClienteCartaoAuthentication remetente,
            ClienteCartaoAuthentication destinatario, double valor) {
        if (extratoCartao.getStatusTransferenciaRole() == StatusTransferenciaRole.APROVADO) {
            double valorAtualizado = remetente.getSaldo() + valor;
            remetente.setSaldo(valorAtualizado);
            clienteCartaoAuthentication.save(remetente);

            double valorAtualizarDestinatario = destinatario.getSaldo() - valor;
            destinatario.setSaldo(valorAtualizarDestinatario);
            clienteCartaoAuthentication.save(destinatario);
            extratoCartao.setStatusTransferenciaRole(StatusTransferenciaRole.ESTORNO);
        }
    }

    public List<ExtratoCartao> listarExtrato(Long id) {
        return cartaoRepository.findByall(id);
    }
}
