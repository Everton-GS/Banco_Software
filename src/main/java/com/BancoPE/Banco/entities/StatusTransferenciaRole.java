package com.BancoPE.Banco.entities;

public enum StatusTransferenciaRole {
    
    APROVADO("Aprovado"),
    ESTORNO("Estrono");

    String status;

    private StatusTransferenciaRole(String status){
        this.status=status;
    }

    public String getStatus() {
        return status;
    }

    
}
