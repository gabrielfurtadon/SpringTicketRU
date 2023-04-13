package br.com.Gabriel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Gabriel.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

}
