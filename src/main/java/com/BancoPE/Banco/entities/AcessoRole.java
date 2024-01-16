package com.BancoPE.Banco.entities;

public enum AcessoRole {
    
    Cliente("cliente"),
    Atendente("atendente"),
    Gerente("gerente");


    private String role;


    private AcessoRole(String role){
        this.role=role;
    }

    public String funcaoRole(){
        return role;
    }
}
