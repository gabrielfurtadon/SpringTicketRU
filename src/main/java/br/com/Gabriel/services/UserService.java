package br.com.Gabriel.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import br.com.Gabriel.entities.User;
import br.com.Gabriel.repositories.UserRepository;
import br.com.Gabriel.utils.*;
import br.com.Gabriel.dto.Exceptions.HandlerException;
import br.com.Gabriel.dto.Mappers.*;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String deleteUser(String ra) {
        User user = userRepository.findByRa(ra);
        if (user == null) {
            return "user not found";
        }
        userRepository.delete(user);
        return "user deleted successfully";
    }

    public List<User> findAll() {

        return userRepository.findAll();
    }

    public User findByRa(String ra) {
        return userRepository.findByRa(ra);
    }

    public User createUser(User user) {
        try {
            boolean emailExists = userRepository.existsByEmailIgnoreCase(user.getEmail());
            boolean raExists = userRepository.existsByRaIgnoreCase(user.getRa());

            if (raExists) {
                throw new HandlerException("ra already exists");
            } else if (emailExists) {
                throw new HandlerException("email is already in use");
            } else {
                user.setPassword(HashManagerUtils.generateCrypt(user.getPassword()));
                // add role USER to user.setRole
                user.setRole(ERole.STUDENT);
                List<String> UserToRolesList = new ArrayList<>();
                UserToRolesList.add(ERole.STUDENT.name());
                user.setRolesList(UserToRolesList);
                userRepository.save(user);
                return user;
            }
        } catch (Exception e) {
            throw new HandlerException(e.getMessage());
        }

    }

    public String updateUser(User user) {
        try {
            User userToUpdate = userRepository.findById(user.getId());

            if (userToUpdate == null) {

                throw new HandlerException("user not found");
            } else {
                System.out.println(userToUpdate);
                userToUpdate.setEmail(user.getEmail());
                userToUpdate.setFirstname(user.getFirstname());
                userToUpdate.setLastname(user.getLastname());
                userToUpdate.setPassword(HashManagerUtils.generateCrypt(user.getPassword()));
                userRepository.save(userToUpdate);
                return "user " + userToUpdate.getFirstname() + " updated successfully";
            }
        } catch (Exception e) {
            throw new HandlerException("Internal server error: could not update user");
        }
    }

    public User findByName(String name) {
        return userRepository.findByNameIgnoreCase(name);
    }



}
