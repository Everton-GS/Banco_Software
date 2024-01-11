package com.BancoPE.Banco.entities;

public enum FuncionarioRole {
    
    Atendente("atendente"),
    Gerente("gerente");


    private String role;


    private FuncionarioRole(String role){
        this.role=role;
    }

    public String funcaoRole(){
        return role;
    }
}
