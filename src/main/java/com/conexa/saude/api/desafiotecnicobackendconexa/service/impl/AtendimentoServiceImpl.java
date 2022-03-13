package com.conexa.saude.api.desafiotecnicobackendconexa.service.impl;

import com.conexa.saude.api.desafiotecnicobackendconexa.config.JwtUtil;
import com.conexa.saude.api.desafiotecnicobackendconexa.entities.Atendimento;
import com.conexa.saude.api.desafiotecnicobackendconexa.entities.Medico;
import com.conexa.saude.api.desafiotecnicobackendconexa.entities.Sintoma;
import com.conexa.saude.api.desafiotecnicobackendconexa.entities.Status;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.exceptions.ApiBusinessException;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.exceptions.InvalidParametersApiException;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.validators.GenericValidator;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.validators.IntegerValidator;
import com.conexa.saude.api.desafiotecnicobackendconexa.repositories.AtendimentoRepository;
import com.conexa.saude.api.desafiotecnicobackendconexa.repositories.MedicoRepository;
import com.conexa.saude.api.desafiotecnicobackendconexa.repositories.PacienteRepository;
import com.conexa.saude.api.desafiotecnicobackendconexa.repositories.SintomaRepository;
import com.conexa.saude.api.desafiotecnicobackendconexa.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AtendimentoServiceImpl implements AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private SintomaRepository sintomaRepository;

    @Override
    @Transactional(readOnly = false)
    public void salvar(Atendimento atendimento, String header) throws ApiBusinessException, InvalidParametersApiException {
        Medico medicoLogado = jwtUtil.getUsuarioLogadoByToken(header.substring(7));
        atendimento.setIdMedicoCriador(String.valueOf(medicoLogado.getIdMedico()));

        validateIntegrity(atendimento);
        atendimento.setStatus(Status.AGENDADO);

        GenericValidator.validate(atendimento);

        Set<Sintoma> sintomas = atendimento.getSintomas();
        Atendimento atendimentoDB = atendimentoRepository.save(atendimento);

        for (Sintoma sintoma : sintomas) {
            sintoma.setIdAtendimento(atendimentoDB.getIdAtendimento());
            sintomaRepository.save(sintoma);
        }
    }

    @Override
    public List<Atendimento> buscarTodos(String header) {
        Medico medicoLogado = jwtUtil.getUsuarioLogadoByToken(header.substring(7));
        List<Atendimento> atendimentosPorMedico = atendimentoRepository.findAllByIdMedicoCriador(String.valueOf(medicoLogado.getIdMedico()));

        atendimentosPorMedico.removeIf(a -> a.getDataHora().isBefore(LocalDateTime.now()));

        return atendimentosPorMedico;
    }

    @Override
    public void cancelar(String id, String header) throws ApiBusinessException {
        Medico medicoLogado = jwtUtil.getUsuarioLogadoByToken(header.substring(7));
        Optional<Atendimento> atendimento = atendimentoRepository.findById(Integer.valueOf(id));

        if(!atendimento.isPresent()) {
            throw new ApiBusinessException("Não se pode cancelar um atendimento inexistente!");
        } else if(!atendimento.get().getIdMedicoCriador().equalsIgnoreCase(String.valueOf(medicoLogado.getIdMedico()))) {
            throw new ApiBusinessException("Apenas o médico criador do agendamento pode cancela-lo");
        } else if(Status.CANCELADO.equals(atendimento.get().getStatus())) {
            throw new ApiBusinessException("O atendimento já está cancelado");
        }

        atendimento.get().setStatus(Status.CANCELADO);
        atendimentoRepository.save(atendimento.get());
    }

    private void validateIntegrity(Atendimento atendimento) throws ApiBusinessException {
        IntegerValidator.validate(String.valueOf(atendimento.getIdMedicoCriador()), false);
        IntegerValidator.validate(String.valueOf(atendimento.getIdPaciente()), false);

        if (atendimento.getDataHora().isBefore(LocalDateTime.now())) {
            throw new ApiBusinessException("Não podemos criar atendimentos para o passado!");
        } else if (!pacienteRepository.existsById(Integer.valueOf(atendimento.getIdPaciente()))) {
            throw new ApiBusinessException("Não podemos criar atendimentos para um paciente inexistente!");
        } else if (!medicoRepository.existsById(Integer.valueOf(atendimento.getIdMedicoCriador()))) {
            throw new ApiBusinessException("Não podemos criar atendimentos para um médico inexistente!");
        }
    }
}
