package com.BancoPE.Banco.entities;

public enum GeneroRole {
    
    Masculino("masculino"),
    Feminino("feminino");

    private String genero;

    
    private GeneroRole(String genero) {
        this.genero = genero;
    }

    public String funcaoRole() {
        return genero;
    }


}
