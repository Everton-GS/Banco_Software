package com.BancoPE.Banco.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "extrato")
public class ExtratoCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "remetente_id")
    private ClienteCartaoAuthentication remetente;

    @ManyToOne
    @JoinColumn(name = "destinatario_id")
    private ClienteCartaoAuthentication destinatario;

    @Column(name = "transacao")
    StatusTransferenciaRole statusTransferenciaRole;

    @NotNull
    @Column(name = "valor")
    private double valor;

    @NotBlank
    @Column(name = "horario")
    private String horario;

    public ExtratoCartao(ClienteCartaoAuthentication remetente, ClienteCartaoAuthentication destinatario,
         double valor) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.valor = valor;
        this.statusTransferenciaRole=StatusTransferenciaRole.APROVADO;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.horario = now.format(formatter);
    }

    

}
