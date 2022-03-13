package com.conexa.saude.api.desafiotecnicobackendconexa.service;

import com.conexa.saude.api.desafiotecnicobackendconexa.entities.Paciente;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.exceptions.ApiBusinessException;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.exceptions.InvalidParametersApiException;

import java.util.List;

public interface PacienteService {

    void salvar(Paciente paciente) throws ApiBusinessException, InvalidParametersApiException;

    void editar(Paciente paciente, String id) throws ApiBusinessException, InvalidParametersApiException;

    void deletar(String id) throws ApiBusinessException;

    Paciente buscarPorId(String id) throws ApiBusinessException;

    List<Paciente> buscarTodos();
}
