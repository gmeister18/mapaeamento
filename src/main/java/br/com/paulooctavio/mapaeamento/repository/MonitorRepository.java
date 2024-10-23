package br.com.paulooctavio.mapaeamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.paulooctavio.mapaeamento.model.Monitor;

public interface MonitorRepository extends JpaRepository<Monitor, Integer> {

    //get by funcionario
    List<Monitor> findByFuncionarioId(Integer id);
}
