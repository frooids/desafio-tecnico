package com.conexa.saude.api.desafiotecnicobackendconexa.repositories;

import com.conexa.saude.api.desafiotecnicobackendconexa.entities.Sintoma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SintomaRepository extends JpaRepository<Sintoma, Integer> {

}
