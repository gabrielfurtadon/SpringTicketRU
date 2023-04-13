package br.com.Gabriel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Gabriel.entities.Aluno;
import br.com.Gabriel.repositories.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repository;
	
	public List<Aluno> findAll() {
		return repository.findAll();
	}
	
	public Aluno findById(Long RA) {
		Optional<Aluno> aluno = repository.findById(RA);
		return aluno.get();
		
	}
	
}
