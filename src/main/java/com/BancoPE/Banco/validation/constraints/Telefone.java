package com.BancoPE.Banco.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.BancoPE.Banco.validation.TelefoneValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = TelefoneValidation.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface Telefone {

    String message() default "Telefone inválido";
    Class<?>[] groups() default {}; // Adicionando o parâmetro groups

    Class<? extends Payload>[] payload() default {};
}
