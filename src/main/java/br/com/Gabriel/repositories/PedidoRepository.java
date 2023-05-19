package br.com.Gabriel.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import br.com.Gabriel.entities.Pedido;


//import jakarta.transaction.Transactional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
        // find by id
        Pedido findById(int id);
        
        

}