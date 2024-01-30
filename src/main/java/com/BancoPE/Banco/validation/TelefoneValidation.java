package com.BancoPE.Banco.validation;

import com.BancoPE.Banco.validation.constraints.Telefone;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TelefoneValidation implements ConstraintValidator<Telefone,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
       
        String place= value==null? "" : value;

        return place.matches("^\\(\\d{2}\\) \\d{4,5}-\\d{4}$");
    }
    
}
