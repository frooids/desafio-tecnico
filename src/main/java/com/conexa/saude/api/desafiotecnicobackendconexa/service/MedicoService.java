package com.conexa.saude.api.desafiotecnicobackendconexa.service;

import com.conexa.saude.api.desafiotecnicobackendconexa.entities.Medico;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.exceptions.ApiBusinessException;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.exceptions.InvalidParametersApiException;

public interface MedicoService {

    void salvar(Medico medico) throws ApiBusinessException, InvalidParametersApiException;
}
