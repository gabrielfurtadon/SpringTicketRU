package br.com.Gabriel.repositories;

import java.util.List;

//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.data.repository.query.Param;

//import com.br.photos.photosCP.Model.Permission;
import br.com.Gabriel.entities.Role;
//import com.br.photos.photosCP.Model.User;

//import jakarta.transaction.Transactional;

// create the interface PhotoRepository
public interface RoleRepository extends PagingAndSortingRepository<Role, Integer>, CrudRepository<Role, Integer> {
    Role findByName(String name);

    Role findByDescriptionIgnoreCase(String description);

    // Role findByPermissions(List<Permission> permissions);
    // Role findByUsers(List<User> users);
    Role findById(int id);

    List<Role> findAll();

}
