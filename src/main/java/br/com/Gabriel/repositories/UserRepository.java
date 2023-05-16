package br.com.Gabriel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import br.com.Gabriel.entities.User;

//import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {
        // find by id
        User findById(int id);
        
        User findByRa(String ra);

        User findByRaIgnoreCase(String ra);

        User findByEmailIgnoreCase(String email);

        User findByNameIgnoreCase(String name);

        User findByUsernameOrEmailIgnoreCase(String username, String email);

        Boolean existsByUsernameIgnoreCase(String username);

        Boolean existsByEmailIgnoreCase(String email);

        Boolean existsByRaIgnoreCase(String ra);

        public List<User> findAll();

}