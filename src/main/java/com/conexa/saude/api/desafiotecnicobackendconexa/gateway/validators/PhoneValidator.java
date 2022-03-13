package com.conexa.saude.api.desafiotecnicobackendconexa.gateway.validators;

import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.exceptions.ApiBusinessException;

public class PhoneValidator {

    public static void validate(String telefone) throws ApiBusinessException {
        if (!telefone.matches("^\\([1-9]{2}\\) [1-9] [0-9]{1}[0-9]{3}\\-[0-9]{4}$")
        && !telefone.matches("^\\([1-9]{2}\\) [0-9]{1}[0-9]{3}\\-[0-9]{4}$")) {
            throw new ApiBusinessException("O numero de telefone: " + telefone + " não é válido, " +
                    "verifique se ele está nos padrões aceitos [ (00) 0 0000-0000 ou (00) 0000-0000 ] e tente novamente!");
        }
    }
}
