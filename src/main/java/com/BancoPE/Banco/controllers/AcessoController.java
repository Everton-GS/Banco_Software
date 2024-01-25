package com.BancoPE.Banco.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.BancoPE.Banco.entities.ClienteCartaoAuthentication;
import com.BancoPE.Banco.entities.FuncionarioAuthentication;
import com.BancoPE.Banco.infra.seguranca.TokenService;
import com.BancoPE.Banco.record.AcessoAplicacaoRecord;
import com.BancoPE.Banco.repository.ClienteCartaoAuthenticationRepository;
import com.BancoPE.Banco.repository.FuncionarioAuthenticationRepository;

@RestController
@RequestMapping("/aplicacao")
public class AcessoController {

    @Autowired
    FuncionarioAuthenticationRepository funcionarioAuthenticationRepository;

    @Autowired
    ClienteCartaoAuthenticationRepository clienteCartaoAuthenticationRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    
    @PostMapping(value = "/acessar/funcionario")
    public ResponseEntity<?> acessarAplicacaoCliente(@RequestBody AcessoAplicacaoRecord acessoLogin) {
        try {
            UserDetails userDetails = funcionarioAuthenticationRepository.findByLogin(acessoLogin.login());
            if (userDetails != null && passwordEncoder.matches(acessoLogin.senha(), userDetails.getPassword())) {
                Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
                String token = tokenService.gerarTokenFuncionario((FuncionarioAuthentication) auth.getPrincipal());
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/acessar/cliente")
    public ResponseEntity<?> acessarAplicacaoFuncionario(@RequestBody AcessoAplicacaoRecord acessoLogin) {
        try {
            UserDetails userDetails2 = clienteCartaoAuthenticationRepository.findByCartao(acessoLogin.login());
            if (userDetails2 != null &&userDetails2.isAccountNonLocked()==true && passwordEncoder.matches(acessoLogin.senha(), userDetails2.getPassword())) {
                Authentication auth2 = new UsernamePasswordAuthenticationToken(userDetails2, null,
                        userDetails2.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth2);
                String token = tokenService.gerarTokenCliente((ClienteCartaoAuthentication) auth2.getPrincipal());
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
