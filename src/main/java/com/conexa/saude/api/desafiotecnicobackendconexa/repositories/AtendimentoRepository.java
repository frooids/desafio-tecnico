package com.conexa.saude.api.desafiotecnicobackendconexa.repositories;

import com.conexa.saude.api.desafiotecnicobackendconexa.entities.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Integer> {

    List<Atendimento> findAllByIdMedicoCriador(String idMedicoCriador);

    Atendimento findByIdAtendimentoAndIdMedicoCriador(String id, String valueOf);
}
