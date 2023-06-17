/*
 * package br.com.Gabriel.config;
 * 
 * import java.time.Instant;
 * import java.util.Arrays;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.boot.CommandLineRunner;
 * import org.springframework.context.annotation.Configuration;
 * import org.springframework.context.annotation.Profile;
 * 
 * import br.com.Gabriel.dto.Mappers.EStatus;
 * import br.com.Gabriel.entities.Pedido;
 * import br.com.Gabriel.entities.User;
 * import br.com.Gabriel.repositories.PedidoRepository;
 * import br.com.Gabriel.repositories.UserRepository;
 * 
 * @Configuration // MOSTAR QUE É UMA CLASSE DE CONFIGURAÇÃO
 * 
 * @Profile("test") // TEM QUE SER IGUAL AO PROFILE DO APLICATION PROPERTIES
 * ,RODA SOMENTE NO PERFIL
 * // DE TESTE
 * public class ApplicationConfig implements CommandLineRunner {
 * 
 * @Autowired // COM ISSO O SPRING VE A DEPENDENCIA E ASSOCIAR UMA INSTANCIA DE
 * USERREPOSITRY
 * // AQUI DENTRO
 * private UserRepository userRepository;
 * 
 * @Autowired
 * private PedidoRepository pedidoRepository;
 * 
 * @Override
 * public void run(String... args) throws Exception { // TUDO QUE TIVER DENTRO
 * DO RUN VAI SER EXECUTADO QUANDO A APP FOR INICIADA
 * 
 * 
 * 
 * 
 * 
 * 
 * User u1 = new User("2347962", "123", "Gabriel", "Neves",
 * "gabriel@gmail.com"); // id null pois vai gerar no banco
 * 
 * 
 * Pedido p1 = new Pedido(1, Instant.parse("2023-04-20T19:53:07Z"), 2,
 * EStatus.PAGO, u1); //PASSANDO O USUARIO PARA FAZER A ASSOCIAÇÃO DESSE OBJ
 * 
 * 
 * //SALVAR NO BANCO como lista (array)
 * userRepository.save(u1);
 * pedidoRepository.save(p1);
 * 
 * 
 * 
 * }
 * 
 * }
 */