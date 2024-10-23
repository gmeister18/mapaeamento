package br.com.paulooctavio.mapaeamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulooctavio.mapaeamento.model.Monitor;
import br.com.paulooctavio.mapaeamento.repository.MonitorRepository;

@RequestMapping("/monitores")
@RestController
public class MonitorController {

    @Autowired
    private MonitorRepository monitorRepository;

    @GetMapping
    public ResponseEntity<List<Monitor>> findAll() {
        return ResponseEntity.ok(this.monitorRepository.findAll());
    }

    @GetMapping("/funcionario/{id}")
    public ResponseEntity<List<Monitor>> findByFuncionarioId(@PathVariable Integer id) {
        return ResponseEntity.ok(this.monitorRepository.findByFuncionarioId(id));
    }
}
