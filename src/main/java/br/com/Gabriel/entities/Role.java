package br.com.Gabriel.entities;

import java.sql.Timestamp;
import java.util.List;

import br.com.Gabriel.dto.Mappers.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter 
@Setter
@Table(name = "roles")
@SQLDelete(sql = "UPDATE roles SET deleted=true WHERE id = ?")
@Where(clause = "deleted = false")
public class Role {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private ERole name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private List<User> users;
    
    @ManyToMany(mappedBy = "roles")
    private List<Permission> permissions;

    @NotNull
    @Column(nullable = false)
    private String description;

    @Column(name = "created_at", insertable = true, updatable = true)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = true, updatable = true)
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Column(name = "deleted", insertable = true, updatable = true)
    private boolean deleted = Boolean.FALSE;

   // private List<String> permissions;
    //private List<User> users;

    public Role() {

    }

    public Role(ERole name, String description, List<Permission> permissions, List<User> users) {
        this.name = name;
        this.description = description;
        this.permissions = permissions;
        this.users = users;
    }

    public Role(ERole name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role [description=" + description + ", id=" + id + ", name=" + name + ", permissions=" + permissions
                + ", users=" + users + "]";
    }
}

