package br.com.paulooctavio.mapaeamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import br.com.paulooctavio.mapaeamento.model.Setor;
import br.com.paulooctavio.mapaeamento.repository.SetorRepository;

@RequestMapping("/setores")
@RestController
public class SetorController {

    @Autowired
    private SetorRepository setorRepository;

    @GetMapping
    public ResponseEntity<List<Setor>> listarSetores() {
        return ResponseEntity.ok(setorRepository.findAllByOrderByNomeAsc());
    }

}
