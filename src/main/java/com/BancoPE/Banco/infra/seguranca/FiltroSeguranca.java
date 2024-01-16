package com.BancoPE.Banco.infra.seguranca;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.BancoPE.Banco.repository.ClienteRepository;
import com.BancoPE.Banco.repository.FuncionarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;

@Component
public class FiltroSeguranca extends OncePerRequestFilter {
    

    @Autowired
    TokenService tokenService;


    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response, @NonNull FilterChain filterChain)throws ServletException, IOException {

        String token= this.recoverToken(request);
        if(token!= null){
            String login = tokenService.validarToken(token);

            UserDetails user;
            
            user=clienteRepository.findByCartao(login);
            if(user!=null){
                var authentication = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else{
               UserDetails usuarioFuncionario =funcionarioRepository.findByNome(login);
                var authentication = new UsernamePasswordAuthenticationToken(usuarioFuncionario,null,usuarioFuncionario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }

   
}
