package br.com.Gabriel.entities;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.Gabriel.dto.Mappers.*;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted=true WHERE id = ?")
@Where(clause = "deleted = false")
public class User implements Serializable, UserDetails {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // colun RA
    @Column(name = "ra", unique = true)
    @NotEmpty(message = "O campo RA nao pode ser vazio")
    private String ra;

    // @NotNull(message = "O campo nome não pode ser vazio")
    @NotEmpty(message = "O campo senha nao pode ser vazio")
    private String password;

    // @NotNull(message = "O Campo primeiro nome não pode ser vazio")
    @Column(name = "firstname")
    @NotEmpty(message = "O campo nome nao pode ser vazio")
    private String firstname;

    // @NotNull(message = "O Campo sobrenome não pode ser vazio")
    @Column(name = "lastname")
    @NotEmpty(message = "O campo sobrenome nao pode ser vazio")
    private String lastname;

    @Column(name = "saldo")
    private int saldo;

    @Column(name = "cpf")
    @NotEmpty(message = "O campo cpf nao pode ser vazio")
    private String cpf;

    // @NotNull(message = "O Campo email não pode ser vazio")
    @Column(name = "email", unique = true)
    @NotEmpty(message = "O campo email nao pode ser vazio")
    private String email;

    @Column(name = "created_at", insertable = true, updatable = true)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = true, updatable = true)
    @UpdateTimestamp
    private Timestamp updatedAt;
    // create a list of strings to store the roles, roleslist will receive
    private List<String> rolesList;

    @Column(name = "ERole")
    // @Enumerated(EnumType.STRING)
    private ERole role;

    // Soft delete
    // @Builder.Default
    @Column(name = "deleted", insertable = true, updatable = true)
    private boolean deleted = Boolean.FALSE;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Pedido> pedidos = new ArrayList<>();

    // methods

    public User() {
        this.role = ERole.STUDENT;
    }

    public User(String ra, String password) {
        this.ra = ra;
        this.password = password;
        this.role = ERole.STUDENT;
    }

    public User(Long id, String ra, String password, String firstname, String lastname, int saldo, String email,
            String cpf) {
        this.id = id;
        this.ra = ra;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.saldo = saldo;
        this.email = email;
        // this.role = ERole.STUDENT;
        this.cpf = cpf;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        // Add any additional roles or authorities here if needed
        return authorities;
    }

    @Override
    public String getUsername() {
        return ra;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
