package com.conexa.saude.api.desafiotecnicobackendconexa.service.impl;

import com.conexa.saude.api.desafiotecnicobackendconexa.entities.Paciente;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.exceptions.ApiBusinessException;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.exceptions.InvalidParametersApiException;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.validators.CpfValidator;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.validators.GenericValidator;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.validators.IntegerValidator;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.validators.PhoneValidator;
import com.conexa.saude.api.desafiotecnicobackendconexa.repositories.PacienteRepository;
import com.conexa.saude.api.desafiotecnicobackendconexa.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public void salvar(Paciente paciente) throws ApiBusinessException, InvalidParametersApiException {
        validateIntegrity(paciente);
        IntegerValidator.validate(paciente.getIdade(), false);
        PhoneValidator.validate(paciente.getTelefone());
        CpfValidator.validate(paciente.getCpf());

        IntegerValidator.validate(paciente.getIdade(), false);

        GenericValidator.validate(paciente);
        pacienteRepository.save(paciente);
    }

    @Override
    public void editar(Paciente paciente, String id) throws ApiBusinessException, InvalidParametersApiException {
        IntegerValidator.validate(paciente.getIdade(), false);
        IntegerValidator.validate(id, false);
        PhoneValidator.validate(paciente.getTelefone());
        CpfValidator.validate(paciente.getCpf());
        Optional<Paciente> pacienteDb = pacienteRepository.findById(Integer.valueOf(id));

        if (pacienteDb.isPresent()) {
            GenericValidator.validate(paciente);
            paciente.setIdPaciente(pacienteDb.get().getIdPaciente());
            pacienteRepository.save(paciente);
        } else {
            throw new ApiBusinessException("Nenhum paciente encontrado para o id: " + id);
        }
    }

    @Override
    public void deletar(String id) throws ApiBusinessException {
        IntegerValidator.validate(id, false);
        Optional<Paciente> pacienteDb = pacienteRepository.findById(Integer.valueOf(id));

        if (pacienteDb.isPresent()) {
            pacienteRepository.deleteById(Integer.valueOf(id));
        } else {
            throw new ApiBusinessException("Nenhum paciente encontrado para o id: " + id);
        }
    }

    @Override
    public Paciente buscarPorId(String id) throws ApiBusinessException {
        IntegerValidator.validate(id, false);
        Optional<Paciente> pacienteDb = pacienteRepository.findById(Integer.valueOf(id));

        if (pacienteDb.isPresent()) {
            return pacienteDb.get();
        } else {
            throw new ApiBusinessException("Nenhum paciente encontrado para o id: " + id);
        }
    }

    @Override
    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }

    private void validateIntegrity(Paciente paciente) throws ApiBusinessException {
        if (pacienteRepository.existsByCpf(paciente.getCpf())) {
            throw new ApiBusinessException("Já existe um paciente cadastrado com este cpf");
        }

        if (pacienteRepository.existsByEmail(paciente.getEmail())) {
            throw new ApiBusinessException("Já existe um paciente cadastrado com este email");
        }
    }

}
