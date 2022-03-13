package com.conexa.saude.api.desafiotecnicobackendconexa.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String mensagem;
    private LocalDateTime timestamp;

}
