package com.BancoPE.Banco.infra.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class Configuracao {
    
    @Autowired
    FiltroSeguranca filtroSeguranca;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.POST,"/agencia/cadastrar").permitAll()
                .requestMatchers(HttpMethod.POST,"/funcionario/registrar").hasRole("gerente")
                .requestMatchers(HttpMethod.PUT,"/funcionario/bloquear").hasRole("gerente")
                .requestMatchers(HttpMethod.PUT,"/funcionario/estorno").hasRole("gerente")
                .requestMatchers(HttpMethod.PUT,"/funcionario/deposito").hasAnyRole("gerete","atendente")
                .requestMatchers(HttpMethod.POST,"/aplicacao/acessar/cliente").permitAll()
                .requestMatchers(HttpMethod.POST,"/aplicacao/acessar/funcionario").permitAll()
                .requestMatchers(HttpMethod.POST,"/cliente/registrar").hasRole("gerente")
                .requestMatchers(HttpMethod.POST,"/cliente/transferencia").hasRole("cliente")
                .requestMatchers(HttpMethod.POST,"/cliente/extrato/listar").hasRole("cliente")
                .anyRequest().authenticated())
                .addFilterBefore(filtroSeguranca,UsernamePasswordAuthenticationFilter.class)
            .httpBasic(Customizer.withDefaults())
            .formLogin(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
