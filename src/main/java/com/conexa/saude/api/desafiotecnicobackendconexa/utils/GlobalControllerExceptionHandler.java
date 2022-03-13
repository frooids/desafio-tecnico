package com.conexa.saude.api.desafiotecnicobackendconexa.utils;

import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.exceptions.ApiBusinessException;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.exceptions.InvalidParametersApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import java.time.LocalDateTime;
import java.util.Set;

@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler({InvalidParametersApiException.class})
    public ResponseEntity<ErrorResponse> handleInvalidParameters(InvalidParametersApiException ex) {
        Set<ConstraintViolation<Object>> erros = ex.getViolations();
        StringBuilder mensagem = new StringBuilder("Lista de erros encontrados: ");

        for (ConstraintViolation<Object> erro : erros) {
            mensagem = mensagem.append(erro.getPropertyPath() + ":" + erro.getMessageTemplate() + "; ");
        }

        return ResponseEntity.badRequest().body(new ErrorResponse(mensagem.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler({ApiBusinessException.class})
    public ResponseEntity<ErrorResponse> handleInvalidParameters(ApiBusinessException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage(), LocalDateTime.now()));
    }

}
