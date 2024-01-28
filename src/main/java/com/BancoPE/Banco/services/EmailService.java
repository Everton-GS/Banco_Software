package com.BancoPE.Banco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public void emailAcesso(String cpf,String email,String senha){

        String assunto="Credencial";
        String mensagem="Seja bem-vindo ao Banco-PE\n"+
                        "\n"+
                         "\nLongin de Acesso:"+cpf+
                         "\n"+
                         "\nSenha  de Acesso:"+senha+
                         "\n";

       SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

       simpleMailMessage.setFrom(sender);
       simpleMailMessage.setTo(email);
       simpleMailMessage.setSubject(assunto);
       simpleMailMessage.setText(mensagem);
       javaMailSender.send(simpleMailMessage);  
             
    }
}
