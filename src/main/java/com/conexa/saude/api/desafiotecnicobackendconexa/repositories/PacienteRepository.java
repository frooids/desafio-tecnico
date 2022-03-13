package com.conexa.saude.api.desafiotecnicobackendconexa.repositories;

import com.conexa.saude.api.desafiotecnicobackendconexa.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    boolean existsById(Integer id);
}
