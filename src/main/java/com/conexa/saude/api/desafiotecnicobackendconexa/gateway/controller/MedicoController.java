package com.conexa.saude.api.desafiotecnicobackendconexa.gateway.controller;

import com.conexa.saude.api.desafiotecnicobackendconexa.entities.Medico;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.validators.GenericValidator;
import com.conexa.saude.api.desafiotecnicobackendconexa.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping("/signup")
    public ResponseEntity<?> cadastro(@RequestBody Medico medico) throws Exception {
        medicoService.salvar(medico);
        return ResponseEntity.ok("Medico cadastrado com sucesso!");
    }

}
