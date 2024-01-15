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
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cartao")
public class ClienteCartao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cartao")
    private Long id;

    @Column(length = 10,name = "cartao")
    private String numeroCartao;

    @Column(length = 6)
    private String senha;

    @Column(name = "saldo")
    private double saldo;

    @Column(name = "vencimento")
    private LocalDateTime vencimento;
}
