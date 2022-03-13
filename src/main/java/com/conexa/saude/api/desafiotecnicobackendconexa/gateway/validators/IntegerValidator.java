package com.conexa.saude.api.desafiotecnicobackendconexa.gateway.validators;

import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.exceptions.ApiBusinessException;

public class IntegerValidator {

    public static void validate(String numero, boolean isIdadeProfissional) throws ApiBusinessException {
        try {
            Integer idade = Integer.parseInt(numero);
            if(isIdadeProfissional && idade < 18){
                throw new ApiBusinessException("O médico precisa ter pelo menos 18 anos para atuar na área");
            }
        } catch (RuntimeException ex) {
            throw new ApiBusinessException(numero + " não é um numero válido");
        }
    }
}
