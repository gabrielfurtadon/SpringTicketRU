package br.com.Gabriel.entities;

import jakarta.persistence.Column;

//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

//import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;

import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Lob;

//jakarta validation for email
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
// what type of not notations are in hibernate.validator.constraints: 
// https://docs.jboss.org/hibernate/validator/5.2/reference/en-US/html_single/#section-builtin-constraints
// there are many notations in this package like notempty, notblank, notnull, etc

import br.com.Gabriel.dto.Mappers.*;

//import lombok.AllArgsConstructor;
//import lombok.Builder;

//import jakarta.validation.constraints.NotNull;

import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted=true WHERE id = ?")
@Where(clause = "deleted = false")
public class User {

    @Id
    @NotNull
    @GeneratedValue
    private int id;

    @Column(name = "username", unique = true)
    @NotEmpty(message = "O campo nome de usuario nao pode ser vazio")
    private String username;

    // @NotNull(message = "O campo nome não pode ser vazio")
    @NotEmpty(message = "O campo senha nao pode ser vazio")
    private String password;

    // @NotNull(message = "O Campo primeiro nome não pode ser vazio")
    @Column(name = "firstname")
    @NotEmpty(message = "O campo nome nao pode ser vazio")
    private String Firstname;

    // @NotNull(message = "O Campo sobrenome não pode ser vazio")
    @Column(name = "lastname")
    @NotEmpty(message = "O campo sobrenome nao pode ser vazio")
    private String Lastname;

    // @NotNull(message = "O Campo email não pode ser vazio")
    @Column(name = "email", unique = true)
    @NotEmpty(message = "O campo email nao pode ser vazio")
    private String email;

    @Column(name = "photo")
    @Lob
    private byte[] photo; // this is for the profile picture, it will be stored as a blob in the database

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

    // methods

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String firstname, String lastname, String email) {
        this.username = username;
        this.password = password;
        this.Firstname = firstname;
        this.Lastname = lastname;
        this.email = email;
    }

    public User(String username, String password, String firstname, String lastname, String email, byte[] photo) {
        this.username = username;
        this.password = password;
        this.Firstname = firstname;
        this.Lastname = lastname;
        this.email = email;
        this.photo = photo;
    }

    public User(String username, String password, String firstname, String lastname, String email, byte[] photo,
            ERole role) {
        this.username = username;
        this.password = password;
        this.Firstname = firstname;
        this.Lastname = lastname;
        this.email = email;
        this.photo = photo;
        this.role = role;
    }
}
