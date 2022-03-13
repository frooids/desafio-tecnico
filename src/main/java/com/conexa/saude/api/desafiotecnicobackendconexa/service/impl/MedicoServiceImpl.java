package com.conexa.saude.api.desafiotecnicobackendconexa.service.impl;

import com.conexa.saude.api.desafiotecnicobackendconexa.entities.Medico;
import com.conexa.saude.api.desafiotecnicobackendconexa.entities.security.Perfil;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.exceptions.ApiBusinessException;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.exceptions.InvalidParametersApiException;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.validators.CpfValidator;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.validators.GenericValidator;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.validators.IntegerValidator;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.validators.PhoneValidator;
import com.conexa.saude.api.desafiotecnicobackendconexa.repositories.MedicoRepository;
import com.conexa.saude.api.desafiotecnicobackendconexa.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void salvar(Medico medico) throws ApiBusinessException, InvalidParametersApiException {
        validateIntegrity(medico);
        IntegerValidator.validate(medico.getIdade(), true);
        PhoneValidator.validate(medico.getTelefone());
        CpfValidator.validate(medico.getCpf());
        encodePassword(medico);
        setPerfilDefaultMedico(medico);

        GenericValidator.validate(medico);
        medicoRepository.save(medico);
    }

    private void setPerfilDefaultMedico(Medico medico) {
        medico.addPerfil(Perfil.MEDICO);
    }

    private void encodePassword(Medico medico) {
        medico.setSenha(bCryptPasswordEncoder.encode(medico.getSenha()));
    }

    private void validateIntegrity(Medico medico) throws ApiBusinessException {
        if (medicoRepository.existsByCpf(medico.getCpf())) {
            throw new ApiBusinessException("Já existe um médico cadastrado com este cpf");
        }

        if (medicoRepository.existsByEmail(medico.getEmail())) {
            throw new ApiBusinessException("Já existe um médico cadastrado com este email");
        }
    }

}
