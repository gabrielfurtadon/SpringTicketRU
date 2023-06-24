package br.com.Gabriel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.Gabriel.dto.ApiResponseDTO;
import br.com.Gabriel.dto.AuthenticationUserDTO;
import br.com.Gabriel.dto.TokenResponseDTO;
import br.com.Gabriel.dto.Exceptions.HandlerException;
import br.com.Gabriel.entities.*;

import br.com.Gabriel.services.UserService;
import br.com.Gabriel.services.UtilityService;

//localhost:8080/users/create
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private UtilityService handleUser;

    @Autowired
    UtilityService utilityService = new UtilityService();

    @GetMapping(value = "/{id}") // PARA FALAR QUE A URL TEM UM PARAMETRO que é o id
    public ResponseEntity<User> findById(@PathVariable Long id) { // PARA O SPRING ACEITAR O ID COMO PARAMETRO QUE VAI
                                                                  // CHEGAR NA URL
        User obj = userService.findById(id);
        return ResponseEntity.ok().body(obj); // JOGANDO O OBJ QUE FOI ACHADO COMO RESPOSTA (ok 200)
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/login", produces = "application/json")
    @ResponseBody
    public ApiResponseDTO login(@RequestBody AuthenticationUserDTO user) {
        try {
            ResponseEntity<String> token;
            System.out.println("token: " + user.getRa() + " " + user.getPassword());
            token = handleUser.execute(user);

            return new ApiResponseDTO(new TokenResponseDTO(token));
        } catch (Exception e) {
            throw new HandlerException(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/me", produces = "application/json")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String ra = ((UserDetails) principal).getUsername(); // Assuming RA is stored in the username field
                User user = userService.findByRa(ra);
                if (user != null) {
                    return ResponseEntity.ok(user);
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/create")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping(value = "/find-by-ra/{ra}")
    public User findByRa(@PathVariable String ra) {

        return userService.findByRa(ra);

    }

    // get saldo by ra
    @GetMapping(value = "/saldo/{ra}")
    public ResponseEntity<Integer> getUserSaldo(@PathVariable String ra) throws NotFoundException {
        int saldo = userService.getSaldo(ra);
        return ResponseEntity.ok().body(saldo);
    }

    @GetMapping(value = "/find-all")
    public List<User> findAllUsers() {

        return userService.findAll();

    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping(value = "/delete/{ra}")
    public String deleteUser(@PathVariable String ra) {

        return userService.deleteUser(ra);

    }

}
