package com.conexa.saude.api.desafiotecnicobackendconexa.service;

import com.conexa.saude.api.desafiotecnicobackendconexa.entities.Atendimento;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.exceptions.ApiBusinessException;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.exceptions.InvalidParametersApiException;

import java.util.List;

public interface AtendimentoService {

    void salvar(Atendimento atendimento, String header) throws ApiBusinessException, InvalidParametersApiException;

    List<Atendimento> buscarTodos(String token);

    void cancelar(String id, String header) throws ApiBusinessException;
}
