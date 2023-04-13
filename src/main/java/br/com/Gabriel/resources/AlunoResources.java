package br.com.Gabriel.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Gabriel.entities.Aluno;
import br.com.Gabriel.services.AlunoService;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoResources {

	@Autowired
	private AlunoService service;
	
	
	@GetMapping
	public ResponseEntity<List<Aluno>> findAll(){
		List<Aluno> alunos = service.findAll();
		return ResponseEntity.ok().body(alunos);
	}
	
	@GetMapping(value = "/{ra}")
	public ResponseEntity<Aluno> findById(@PathVariable Long ra) {
			Aluno aluno = service.findById(ra);
			return ResponseEntity.ok().body(aluno);
	}
	
}
