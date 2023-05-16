package br.com.Gabriel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

//localhost:8080/user/create
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private UtilityService handleUser;

    @Autowired
    UtilityService utilityService = new UtilityService();

    @PostMapping(value = "/login", produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ApiResponseDTO login(@RequestBody AuthenticationUserDTO user) {
        try {
            ResponseEntity<String> token;
            token = handleUser.execute(user);
            return new ApiResponseDTO(new TokenResponseDTO(token));
        } catch (Exception e) {
            throw new HandlerException(e.getMessage());
        }
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

    @GetMapping(value = "/find-by-name/{name}")
    public User findByName(@PathVariable String name) {

        return userService.findByName(name);

    }

    @GetMapping(value = "/find-all")
    public List<User> findAllUsers() {

        return userService.findAll();

    }

    @DeleteMapping(value = "/delete/{ra}")
    public String deleteUser(@PathVariable String ra) {

        return userService.deleteUser(ra);

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping(path = "/update")
    public String updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    /*
     * if(user.getRole().getName().equals(ERole.ADMIN.toString())) {
     * // allow user to access admin resources
     * } else {
     * // redirect user to access denied page
     * }
     */
}
