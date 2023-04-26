package br.com.Gabriel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.photos.photosCP.Model.Permission; //NAO EXISTE
import com.br.photos.photosCP.Repository.PermissionRepository; //NAO EXISTE

import jakarta.transaction.Transactional;

@Transactional
@Service
public class PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    public String deletePermission(int id) {
        Permission permission = permissionRepository.findById(id);
        if (permission == null) {
            return "permission not found";
        }
        permissionRepository.delete(permission);
        return "permission deleted successfully";
    }

    public Permission updatePermission(Permission permission) {
        Permission permissionToUpdate = permissionRepository.findById(permission.getId());
        if(permissionToUpdate!=null){
            permissionToUpdate.setName(permission.getName());
            permissionToUpdate.setDescription(permission.getDescription());
            permissionToUpdate.setRoles(permission.getRoles());
            return permissionRepository.save(permissionToUpdate);
        }
        return null;
    }

    public Iterable<Permission> findAll() {

        return permissionRepository.findAll();
    }

    public Permission findById(int id) {
        
        return permissionRepository.findById(id);
    }

    public Permission createPermission(Permission permission) {
        permissionRepository.save(permission);

        return permission;
    }

    public Permission updatePermission2(int id, Permission permission) {
        return null;
    }    
}
