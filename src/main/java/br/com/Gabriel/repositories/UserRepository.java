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

        User findByEmailIgnoreCase(String email);

        User findByUsernameIgnoreCase(String username);

        User findByUsernameOrEmailIgnoreCase(String username, String email);

        Boolean existsByUsernameIgnoreCase(String username);

        Boolean existsByEmailIgnoreCase(String email);

        public List<User> findAll();

}