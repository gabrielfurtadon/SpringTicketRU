package br.com.Gabriel.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.Gabriel.entities.Aluno;
import br.com.Gabriel.repositories.AlunoRepository;

@Configuration
@Profile("ticket")
public class TicketConfig implements CommandLineRunner{
	
	@Autowired
	private AlunoRepository alunoRepository;
	
		
		@Override
		public void run(String... args) throws Exception {
			Aluno a1 = new Aluno(2534573L, "Gabriel Furtado Neves", "123456");
			Aluno a2 = new Aluno(2534574L, "Laura Valente Pereira Ferreira", "123456");
			alunoRepository.saveAll(Arrays.asList(a1, a2));
		}
		
		
		
	}
	
	


