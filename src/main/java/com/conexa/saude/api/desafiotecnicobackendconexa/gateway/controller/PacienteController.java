package com.conexa.saude.api.desafiotecnicobackendconexa.gateway.controller;

import com.conexa.saude.api.desafiotecnicobackendconexa.entities.Paciente;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.validators.GenericValidator;
import com.conexa.saude.api.desafiotecnicobackendconexa.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/patients")
    public ResponseEntity<?> cadastro(@RequestBody Paciente paciente) throws Exception {
        pacienteService.salvar(paciente);
        return ResponseEntity.ok("Paciente cadastrado com sucesso!");
    }

    @PutMapping("/patients/{id}")
    public ResponseEntity<?> alteracao(@RequestBody Paciente paciente, @PathVariable Integer id) throws Exception {
        pacienteService.editar(paciente, String.valueOf(id));
        return ResponseEntity.ok("Paciente alterado com sucesso!");
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<?> delecao(@PathVariable Integer id) throws Exception {
        pacienteService.deletar(String.valueOf(id));
        return ResponseEntity.ok("Paciente deletado com sucesso!");
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(pacienteService.buscarPorId(String.valueOf(id)));
    }

    @GetMapping("/patients")
    public ResponseEntity<?> buscarTodos() {
        return ResponseEntity.ok().body(pacienteService.buscarTodos());
    }
}