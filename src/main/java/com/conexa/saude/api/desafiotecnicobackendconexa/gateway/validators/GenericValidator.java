package com.conexa.saude.api.desafiotecnicobackendconexa.gateway.validators;

import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.exceptions.InvalidParametersApiException;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Slf4j
public class GenericValidator {

    private static ValidatorFactory factory;
    private static Validator validator;

    public static void validate(Object o) throws InvalidParametersApiException {
        Set<ConstraintViolation<Object>> violations = validator.validate(o);
        if (violations.size() > 0) {
            throw new InvalidParametersApiException(violations);
        }
    }

    static {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private GenericValidator(){}
}
