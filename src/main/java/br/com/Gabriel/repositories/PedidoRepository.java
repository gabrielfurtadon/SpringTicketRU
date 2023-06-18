package br.com.Gabriel.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Gabriel.entities.Pedido;
import br.com.Gabriel.entities.User;


//import jakarta.transaction.Transactional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
        // find by id
        Pedido findById(int id);
        
       // Pedido findByUser(long id);
        
        List<Pedido> findByUser(User user);
        
        

}