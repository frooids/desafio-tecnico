package com.conexa.saude.api.desafiotecnicobackendconexa.repositories;

import com.conexa.saude.api.desafiotecnicobackendconexa.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    Medico findByEmail(String email);

    boolean existsByIdMedico(Integer id);
}
