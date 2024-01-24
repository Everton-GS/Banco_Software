package com.BancoPE.Banco.entities;



import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Column(name = "remetente")
    ClienteCartaoAuthentication remetente;

    @Column(name = "destinatario")
    ClienteCartaoAuthentication destinatario;

    @Column(name = "valor")
    private double valor;

    @Column(name = "horario")
    private LocalDateTime horario;



}
