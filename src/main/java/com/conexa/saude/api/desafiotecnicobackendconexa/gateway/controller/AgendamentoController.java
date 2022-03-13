package com.conexa.saude.api.desafiotecnicobackendconexa.gateway.controller;

import com.conexa.saude.api.desafiotecnicobackendconexa.entities.Atendimento;
import com.conexa.saude.api.desafiotecnicobackendconexa.gateway.validators.GenericValidator;
import com.conexa.saude.api.desafiotecnicobackendconexa.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AgendamentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @PostMapping("/attendance")
    public ResponseEntity<?> criaAtendimento(@RequestBody Atendimento atendimento, @RequestHeader("Authentication") String header) throws Exception {
        atendimentoService.salvar(atendimento, header);
        return ResponseEntity.ok("Atendimento agendado com sucesso!");
    }

    @GetMapping("/attendance")
    public ResponseEntity<?> buscarTodos(@RequestHeader("Authentication") String header) {
        List<Atendimento> atendimentos = atendimentoService.buscarTodos(header);
        return ResponseEntity.ok(atendimentos);
    }

    @DeleteMapping("/attendance/{id}")
    public ResponseEntity<?> cancelaAtendimento(@PathVariable Integer id, @RequestHeader("Authentication") String header) throws Exception {
        atendimentoService.cancelar(String.valueOf(id), header);
        return ResponseEntity.ok("Atendimento cancelado com sucesso!");
    }

}
