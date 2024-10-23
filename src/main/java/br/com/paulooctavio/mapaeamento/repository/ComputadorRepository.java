package br.com.paulooctavio.mapaeamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.paulooctavio.mapaeamento.model.Computador;

public interface ComputadorRepository extends JpaRepository<Computador, Integer> {

    //get by funcionario
    List<Computador> findByFuncionarioId(Integer id);
}
