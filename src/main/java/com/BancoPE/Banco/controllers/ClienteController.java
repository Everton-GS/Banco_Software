package com.BancoPE.Banco.controllers;


import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.BancoPE.Banco.entities.Cliente;
import com.BancoPE.Banco.entities.ClienteCartaoAuthentication;
import com.BancoPE.Banco.record.ClienteRegistrarRecord;
import com.BancoPE.Banco.record.ClienteTransferenciaValor;
import com.BancoPE.Banco.repository.ClienteCartaoAuthenticationRepository;
import com.BancoPE.Banco.repository.ClienteRepository;
import com.BancoPE.Banco.services.ClienteCartaoService;
import com.BancoPE.Banco.services.ClienteService;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
    
    @Autowired
    ClienteService clienteService;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteCartaoAuthenticationRepository cartaoAuthenticationRepository;

    @Autowired
    ClienteCartaoService cartaoService;

    @PostMapping("/registrar")
    public ResponseEntity<?>registrar(@RequestBody ClienteRegistrarRecord clienteRegistrar){
       try{
           Optional<Cliente> clienteP = clienteRepository.findByCpf(clienteRegistrar.cpf());
            if(clienteP.isEmpty()){
                Cliente cliente= new Cliente(clienteRegistrar.nome(), clienteRegistrar.cpf(), clienteRegistrar.genero(), clienteRegistrar.nascimento(), clienteRegistrar.endereco(), clienteRegistrar.telefone(), clienteRegistrar.email(),clienteRegistrar.cargRole());
                clienteService.registrar(cliente);

                Optional<ClienteCartaoAuthentication> cartao=cartaoAuthenticationRepository.findByUltimoCartao();
                String cartaoUltimo=cartao.get().getNumeroCartao();

                String [] parte=cartaoUltimo.split("-");
                String segundaparte=parte[1];
                int i = Integer.parseInt(segundaparte);
                i++;

                DecimalFormat decimalFormat = new DecimalFormat("000000");
                String numeroFormatado= decimalFormat.format(i);

                StringBuilder builder= new StringBuilder();
                builder.append(parte[0]).append("-").append(numeroFormatado);
                String resultado =builder.toString();
                LocalDate dataVencimento = LocalDate.now().plusYears(4);

                String senha= new BCryptPasswordEncoder().encode("159357");

                ClienteCartaoAuthentication clienteCartaoAuthentication = new ClienteCartaoAuthentication(resultado,senha, cliente, 0, dataVencimento);
                cartaoService.registrar(clienteCartaoAuthentication);
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.badRequest().build();
            }
       }catch(Exception e){
        return ResponseEntity.internalServerError().build();
       }    
    }

    @PostMapping("/transferencia")
    public ResponseEntity<?> transferenciaValor(@RequestBody ClienteTransferenciaValor transferenciaValor){
        try {
            ClienteCartaoAuthentication cliente = (ClienteCartaoAuthentication) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            ClienteCartaoAuthentication destinatario = cartaoAuthenticationRepository.findByCartao(transferenciaValor.destinatario());
            if(cliente.getSaldo()<transferenciaValor.valor()){
                return ResponseEntity.badRequest().build();
            }
            if(destinatario!=null&& cliente!=destinatario){
              cartaoService.transferenciaValor(transferenciaValor.valor(), cliente, destinatario);
              return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }





}
