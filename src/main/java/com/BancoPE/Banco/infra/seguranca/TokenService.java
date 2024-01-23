package com.BancoPE.Banco.infra.seguranca;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.BancoPE.Banco.entities.ClienteCartaoAuthentication;
import com.BancoPE.Banco.entities.FuncionarioAuthentication;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secrete;

    public String gerarTokenCliente(ClienteCartaoAuthentication cliente) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secrete);
            String token = JWT.create()
                    .withIssuer("Banco_PE")
                    .withSubject(cliente.getUsername())
                    .withExpiresAt(TempoToken())
                    .sign(algorithm);
            return token;

        } catch (JWTCreationException jwt) {
            throw new RuntimeException("Erro while generating token", jwt);
        }
    }
    public String gerarTokenFuncionario(FuncionarioAuthentication funcionario){
        try {
            Algorithm algorithm= Algorithm.HMAC512(secrete);
            String token= JWT.create()
                    .withIssuer("Banco_PE")
                    .withSubject(funcionario.getLogin())
                    .withClaim("role",funcionario.getFuncionario().getCargo().funcaoRole())
                    .withExpiresAt(TempoToken())
                    .sign(algorithm);
                    return token;
            
        } catch (JWTCreationException JWT) {
            throw new RuntimeException("Erro while generating token", JWT);
        }
    }

    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secrete);
            return JWT.require(algorithm)
                    .withIssuer("Banco_PE")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException validarToken) {
            return "";
        }
    }

    private Instant TempoToken() {
        return LocalDateTime.now().plusMinutes(30).toInstant(ZoneOffset.of("-03:00"));
    }
}
