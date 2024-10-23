package br.com.paulooctavio.mapaeamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;  

import java.util.List;

import br.com.paulooctavio.mapaeamento.model.Computador;
import br.com.paulooctavio.mapaeamento.service.ComputadorService;

@RestController
@RequestMapping("/computadores")
public class ComputadorController {

    @Autowired
    private ComputadorService computadorService;

    @GetMapping
    public ResponseEntity<List<Computador>> findAll() {
        return ResponseEntity.ok(this.computadorService.findAll());
    }

    @GetMapping("/funcionario/{id}")
    public ResponseEntity<List<Computador>> findByFuncionarioId( @PathVariable Integer id) {
        return ResponseEntity.ok(this.computadorService.findByFuncionarioId(id));
    }

}
