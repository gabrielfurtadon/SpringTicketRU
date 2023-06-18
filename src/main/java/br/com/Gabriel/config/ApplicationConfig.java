package br.com.Gabriel.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.Gabriel.dto.Mappers.EStatus;
import br.com.Gabriel.entities.Pedido;
import br.com.Gabriel.entities.User;
import br.com.Gabriel.repositories.PedidoRepository;
import br.com.Gabriel.repositories.UserRepository;

@Configuration // MOSTAR QUE É UMA CLASSE DE CONFIGURAÇÃO
@Profile("ticket") // TEM QUE SER IGUAL AO PROFILE DO APLICATION PROPERTIES ,RODA SOMENTE NO PERFIL
					// DE TESTE
public class ApplicationConfig implements CommandLineRunner {

	@Autowired // COM ISSO O SPRING VE A DEPENDENCIA E ASSOCIAR UMA INSTANCIA DE USERREPOSITRY
				// AQUI DENTRO
	private UserRepository userRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Override
	public void run(String... args) throws Exception { // TUDO QUE TIVER DENTRO DO RUN VAI SER EXECUTADO QUANDO A APP
														// FOR INICIADA

		// @Bean
		// public void stardDB() {

		User u2 = new User(2L, "2348063", "123", "Laura", "Valente", 0, "laura@gmail.com");
		User u1 = new User(1L, "2347962", "123", "Gabriel", "Neves", 0, "gabriel@gmail.com"); // id null pois vai gerar
																								// no banco
		Pedido p1 = new Pedido(1, Instant.now(), 2, 0, EStatus.PAGO, u1); // PASSANDO O USUARIO PARA FAZER A ASSOCIAÇÃO
																			// DESSE OBJ
		Pedido p2 = new Pedido(2, Instant.now(), 1, 0, EStatus.AGUARDANDOPAG, u1);
		Pedido p3 = new Pedido(3, Instant.now(), 1, 0, EStatus.AGUARDANDOPAG, u2);
		Pedido p4 = new Pedido(4, Instant.now(), 3, 0, EStatus.AGUARDANDOPAG, u2);
		Pedido p5 = new Pedido(5, Instant.now(), 1, 0, EStatus.AGUARDANDOPAG, u1);

		// SALVAR NO BANCO como lista (array)
		userRepository.saveAll(Arrays.asList(u1, u2));
		pedidoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		// teste

	}

}
