package br.com.paulooctavio.mapaeamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import br.com.paulooctavio.mapaeamento.model.Setor;

public interface SetorRepository extends JpaRepository<Setor, Integer> {
    // ordena os setores por nome
    List<Setor> findAllByOrderByNomeAsc();
}