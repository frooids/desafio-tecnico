package com.conexa.saude.api.desafiotecnicobackendconexa.gateway.validators;

import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.exceptions.ApiBusinessException;

public class CpfValidator {

    public static void validate(String cpf) throws ApiBusinessException {
        if(!cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$")){
            throw new ApiBusinessException("O numero do cpf: " + cpf + " não é válido, " +
                    "verifique se ele está nos padrões aceitos 000.000.000-00 e tente novamente!");
        }
    }
}
