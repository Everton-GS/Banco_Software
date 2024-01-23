package com.BancoPE.Banco.entities;


import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "cartao")
public class ClienteCartaoAuthentication implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cartao")
    private Long id;

    @NotBlank
    @Column(length = 10,name = "cartao")
    private String numeroCartao;

    @NotBlank
    @Column(length = 255)
    private String senha;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    Cliente cliente;

    @Column(name = "saldo")
    private double saldo;

    @NotNull
    @Column(name = "vencimento")
    private LocalDate vencimento;

    
    public ClienteCartaoAuthentication(String numeroCartao,String senha, Cliente cliente,
            double saldo, LocalDate vencimento) {
        this.numeroCartao = numeroCartao;
        this.senha = senha;
        this.cliente = cliente;
        this.saldo = saldo;
        this.vencimento = vencimento;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(cliente.getCargo().funcaoRole()));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return numeroCartao;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
       return true;
    }
}
