package com.BancoPE.Banco.entities;

public enum AcessoRole {
    
    Cliente("ROLE_cliente"),
    Atendente("ROLE_atendente"),
    Gerente("ROLE_gerente");


    private String role;


    private AcessoRole(String role){
        this.role=role;
    }

    public String funcaoRole(){
        return role;
    }
}
