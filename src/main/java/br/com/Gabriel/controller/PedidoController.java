package br.com.Gabriel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Gabriel.entities.Pedido;
import br.com.Gabriel.entities.User;
import br.com.Gabriel.services.PedidoService;
import br.com.Gabriel.services.UserService;

//localhost:8080/user/create
@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService controller;
	
	@Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private UserService userService;
	
	//METODO ENDPOINT PARA ACESSAR OS PEDIDOS
		@GetMapping //MOSTRAR QUE ESSE METODO RESPONDE A UMA REQUISIÇÃO TIPO GET 
		public ResponseEntity<List<Pedido>> findAll() {
			// Pedido u = new Pedido(1L, "Gabriel", "991580417", "gabriel@gmail.com", "gabriel123"); instancção manual mocada, nao precida depois que cria o repository
			List<Pedido> list = controller.findAll();
			return ResponseEntity.ok().body(list); //ok -> retorna resposta com sucesso no HTTP | .body -> corpo da resposta usuario u
		}
		
		@GetMapping(value = "/{id}")  // PARA FALAR QUE A URL TEM UM PARAMETRO que é o id 
		public ResponseEntity<Pedido> findById(@PathVariable int id)	{ //PARA O SPRING ACEITAR O ID COMO PARAMETRO QUE VAI CHEGAR NA URL
			Pedido obj = controller.findById(id);
			return ResponseEntity.ok().body(obj); //JOGANDO O OBJ QUE FOI ACHADO COMO RESPOSTA 
		}
		
		// "HISTÓRICO" DE COMPRA DE FICHAS 
		@GetMapping(value = "/user/{userId}")
	    public ResponseEntity<List<Pedido>> findPedidosByUser(@PathVariable Long userId) {
	        User user = userService.findById(userId);
	        if (user == null) {
	            return ResponseEntity.notFound().build();
	        }
	        List<Pedido> pedidos = pedidoService.findPedidosByUser(user);
	        return ResponseEntity.ok().body(pedidos);
	    }

		
		
		
		
	

	





		
		
   
}
