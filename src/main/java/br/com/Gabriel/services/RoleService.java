package br.com.Gabriel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Gabriel.entities.Role;
import br.com.Gabriel.repositories.RoleRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public String deleteRole(int id) {
        Role role = roleRepository.findById(id);
        if (role == null) {
            return "role not found";
        }
        roleRepository.delete(role);
        return "role deleted successfully";
    }

    public Role updateRole(Role role) {
        Role existingRole = roleRepository.findById(role.getId());
        if (existingRole != null) {
            existingRole.setName(role.getName());
            existingRole.setDescription(role.getDescription());
            existingRole.setPermissions(role.getPermissions());
            existingRole.setUsers(role.getUsers());
            return roleRepository.save(existingRole);
        }
        return null;
    }

    public List<Role> findAll() {

        return roleRepository.findAll();
    }

    public Role findById(int id) {
        return roleRepository.findById(id);
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }
}
