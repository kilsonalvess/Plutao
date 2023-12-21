package br.edu.ifpb.pweb2.plutao.model;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class CodMatriculaConstraintValidator implements ConstraintValidator<CodMatricula, String> {

    private String prefix;

    @Override
    public void initialize(CodMatricula c) {
       prefix = c.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
       boolean resultado;

       if(value != null){
           resultado = value.startsWith(prefix);
       }
       else{
           resultado = false;
       }
       return resultado;
    }
}
