package br.edu.ifpb.pweb2.plutao.model;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CodMatriculaConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CodMatricula {

    public String value() default "2019";

    public String message() default "Deve iniciar por 2019";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
