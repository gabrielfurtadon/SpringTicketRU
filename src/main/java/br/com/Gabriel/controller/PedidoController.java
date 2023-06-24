package br.com.Gabriel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.Gabriel.dto.IdWrapperDTO;
import br.com.Gabriel.dto.PedidoDTO;
import br.com.Gabriel.entities.Pedido;
import br.com.Gabriel.entities.User;
import br.com.Gabriel.services.PedidoService;
import br.com.Gabriel.services.UserService;

//localhost:8080/user/create
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	// "HISTÓRICO" DE COMPRA DE FICHAS createPedido(int, String)

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/create")
	public Pedido createUser(@RequestBody Pedido pedido) {
		return pedidoService.createPedido(pedido);
	}

	// find-by-id
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/find-by-id/{id}")
	public Pedido findById(@PathVariable int id) {
		return pedidoService.findById(id);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/find-all/{ra}")
	public List<Pedido> findAllPedidosByRa(@PathVariable String ra) {
		return pedidoService.findAllPedidosByRa(ra);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/find-by-ra/{ra}")
	public Pedido findByRa(@PathVariable int ra) {
		return pedidoService.findById(ra);
	}

	// payPedido
	@PostMapping(value = "/pay-pedido/{id}")
	public ResponseEntity<Pedido> payPedido(@RequestBody IdWrapperDTO idWrapper) {
		int id = idWrapper.getId();
		Pedido pedido = pedidoService.payPedido(id); // modify this method to return a Pedido
		return ResponseEntity.ok(pedido); // include the Pedido in the response
	}

	// deletePedido

	// cancelPedido
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/cancel-pedido")
	public ResponseEntity<Pedido> cancelPedido(@RequestBody int id) {
		pedidoService.cancelPedido(id);
		return ResponseEntity.ok().build();
	}

}
