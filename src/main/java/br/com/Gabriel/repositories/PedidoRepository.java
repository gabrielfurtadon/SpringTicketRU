package br.com.Gabriel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.Gabriel.entities.Pedido;
import br.com.Gabriel.entities.User;

//import jakarta.transaction.Transactional;
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
        // find by id
        Pedido findById(int id);

        Pedido findByRa(String ra);

        List<Pedido> findByUser(User user);

}