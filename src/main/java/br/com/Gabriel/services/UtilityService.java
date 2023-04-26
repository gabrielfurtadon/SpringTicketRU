package br.com.Gabriel.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.photos.photosCP.Configuration.JwtService;
import com.br.photos.photosCP.DTO.AuthenticationUserDTO;
import com.br.photos.photosCP.DTO.Mappers.ERole;
import com.br.photos.photosCP.Model.Role;
import com.br.photos.photosCP.Model.User;
import com.br.photos.photosCP.Repository.UserRepository;
import com.br.photos.photosCP.Utils.HashManagerUtils;

@Service
public class UtilityService {

    @Autowired
    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtHandler;

    public User findByUsername(String id) {
        return userRepository.findByUsernameIgnoreCase(id);
    }

    public ResponseEntity<String> execute(AuthenticationUserDTO user) {

        User result = findByUsername(user.getUsername());

        List<User> idFound = getUsers(result);

        User userFound = idFound.get(0);

        if (userFound == null || !userFound.getUsername().equals(user.getUsername())
                || !Boolean.TRUE.equals(HashManagerUtils.validateHash(user.getPassword(), userFound.getPassword()))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
        }

        String token = jwtHandler.generateToken(userFound.getUsername(), userFound.getRole());

        return ResponseEntity.ok(token);
    }

    public List<User> getUsers(User users) {
        List<User> list = new ArrayList<>();
        list.add(users);
        return list;
    }

    public String addRoleToUser(User user) {
        User userToUpdate = userRepository.findById(user.getId());
        if (userToUpdate == null) {
            return "user not found";
        }
        Set<Role> roles = new HashSet<>();
        roles.addAll(userToUpdate.getRoles());
        roles.addAll(user.getRoles());
        userToUpdate.setRoles(roles);

        List<String> rolesToRolesList = new ArrayList<>();

        for (Role role : user.getRoles()) {
            switch (role.getName()) {
                case ADMINISTRATOR:
                    userToUpdate.setRole(ERole.ADMINISTRATOR);
                    rolesToRolesList.add(ERole.ADMINISTRATOR.name());
                    userToUpdate.setRolesList(rolesToRolesList);
                    break;
                case MODERATOR:
                    userToUpdate.setRole(ERole.MODERATOR);

                    rolesToRolesList.add(ERole.MODERATOR.name());
                    userToUpdate.setRolesList(rolesToRolesList);

                    break;
                case USER:
                    userToUpdate.setRole(ERole.USER);

                    rolesToRolesList.add(ERole.USER.name());
                    userToUpdate.setRolesList(rolesToRolesList);

                    break;
                case GUEST:
                    userToUpdate.setRole(ERole.GUEST);

                    rolesToRolesList.add(ERole.GUEST.name());
                    userToUpdate.setRolesList(rolesToRolesList);
                    break;

                case PREMIUM_USER:
                    userToUpdate.setRole(ERole.PREMIUM_USER);

                    rolesToRolesList.add(ERole.PREMIUM_USER.name());
                    userToUpdate.setRolesList(rolesToRolesList);

                    break;
                case ANALYTICS:
                    userToUpdate.setRole(ERole.ANALYTICS);

                    rolesToRolesList.add(ERole.ANALYTICS.name());
                    userToUpdate.setRolesList(rolesToRolesList);

                    break;
                case MARKETING:
                    userToUpdate.setRole(ERole.MARKETING);

                    rolesToRolesList.add(ERole.MARKETING.name());
                    userToUpdate.setRolesList(rolesToRolesList);

                    break;
                case SALES:
                    userToUpdate.setRole(ERole.SALES);

                    rolesToRolesList.add(ERole.SALES.name());
                    userToUpdate.setRolesList(rolesToRolesList);

                    break;
                case CUSTOMER_SERVICE:
                    userToUpdate.setRole(ERole.CUSTOMER_SERVICE);

                    rolesToRolesList.add(ERole.CUSTOMER_SERVICE.name());
                    userToUpdate.setRolesList(rolesToRolesList);

                    break;
                case BANNED:
                    userToUpdate.setRole(ERole.BANNED);

                    rolesToRolesList.add(ERole.BANNED.name());
                    userToUpdate.setRolesList(rolesToRolesList);
                    break;
                default:
                    break;
            }
        }

        userRepository.save(userToUpdate);
        return "role added successfully";
    }

    public String addRoleToUser2(User user) {
        User userToUpdate = userRepository.findById(user.getId());
        if (userToUpdate == null) {
            return "user not found";
        }
        Set<Role> roles = new HashSet<>(userToUpdate.getRoles());
        roles.addAll(user.getRoles());
        userToUpdate.setRoles(roles);

        List<String> rolesToRolesList = new ArrayList<>();
        for (Role role : user.getRoles()) {
            ERole eRole = ERole.valueOf(role.getName().toString());
            userToUpdate.setRole(eRole);
            rolesToRolesList.add(eRole.name());
        }

        userToUpdate.setRolesList(rolesToRolesList);
        userRepository.save(userToUpdate);
        return "role added successfully";
    }

    public String removeRoleFromUser(User user) {
        User userToUpdate = userRepository.findById(user.getId());
        if (userToUpdate == null) {
            return "user not found";
        }
        Set<Role> roles = new HashSet<>();
        roles.addAll(userToUpdate.getRoles());
        roles.removeAll(user.getRoles());
        userToUpdate.setRoles(roles);

        for (Role role : user.getRoles()) {
            switch (role.getName()) {
                case ADMINISTRATOR:
                    userToUpdate.setRole(ERole.USER);
                    break;
                case MODERATOR:
                    userToUpdate.setRole(ERole.USER);
                    break;
                case USER:
                    userToUpdate.setRole(ERole.GUEST);
                    break;
                case GUEST:
                    userToUpdate.setRole(ERole.GUEST);
                    break;
                case PREMIUM_USER:
                    userToUpdate.setRole(ERole.USER);
                    break;
                case ANALYTICS:
                    userToUpdate.setRole(ERole.USER);
                    break;
                case MARKETING:
                    userToUpdate.setRole(ERole.USER);
                    break;
                case SALES:
                    userToUpdate.setRole(ERole.USER);
                    break;
                case CUSTOMER_SERVICE:
                    userToUpdate.setRole(ERole.USER);
                    break;
                case BANNED:
                    userToUpdate.setRole(ERole.USER);
                    break;
                default:
                    break;
            }
        }

        userRepository.save(userToUpdate);
        return "role removed successfully";
    }

    /*
     * {
     * "id": 552,
     * "roles": [
     * {
     * "id": 402,
     * "name": "ADMINISTRATOR"
     * }
     * ]
     * }
     */
}
