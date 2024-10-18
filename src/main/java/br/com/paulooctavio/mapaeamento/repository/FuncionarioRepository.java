package br.com.paulooctavio.mapaeamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.paulooctavio.mapaeamento.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

}
