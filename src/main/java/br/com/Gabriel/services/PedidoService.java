package br.com.Gabriel.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import br.com.Gabriel.entities.Pedido;
import br.com.Gabriel.entities.User;
import br.com.Gabriel.repositories.PedidoRepository;
import br.com.Gabriel.repositories.UserRepository;
import br.com.Gabriel.utils.*;
import br.com.Gabriel.dto.Exceptions.HandlerException;
import br.com.Gabriel.dto.Mappers.*;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    public String deletePedido(int id) {
        Pedido pedido = pedidoRepository.findById(id);
        if (pedido == null) {
            return "pedido not found";
        }
        pedidoRepository.delete(pedido);
        return "peido deleted successfully";
    }

    public List<Pedido> findAll() {

        return pedidoRepository.findAll();
    }

    public Pedido findById(int id) {
        return pedidoRepository.findById(id);
    }

	public List<Pedido> findPedidosByUser(User user) {
		// TODO Auto-generated method stub
		return pedidoRepository.findByUser(user);
	}
    
//    public List<User> findByUser(Long id) {
//    	
//    	return pedidoRepository.findByUser(1L);
//    }

    

   
    



}
