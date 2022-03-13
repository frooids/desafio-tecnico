package com.conexa.saude.api.desafiotecnicobackendconexa.gateway.exceptions;

import lombok.Data;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Set;

@Data
public class InvalidParametersApiException extends Exception {

    private Set<ConstraintViolation<Object>> violations = new HashSet<>();

    public InvalidParametersApiException(Set<ConstraintViolation<Object>> violations) {
        super("Erro ao validar dados..");
        this.violations = violations;
    }
}
