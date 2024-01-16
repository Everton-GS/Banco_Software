package com.BancoPE.Banco.entities;

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
@Table(name = "agencia")
public class Agencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agencia")
    private Long id;

    @Column(length =4, name = "agencia" )
    private String numero;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "contato_agencia")
    private String telefoneAgencia;

    public Agencia(String numero, String endereco, String telefoneAgencia) {
        this.numero = numero;
        this.endereco = endereco;
        this.telefoneAgencia = telefoneAgencia;
    }

    
}
