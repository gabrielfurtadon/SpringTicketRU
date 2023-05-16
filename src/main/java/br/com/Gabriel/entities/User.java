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

import br.com.Gabriel.dto.Mappers.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted=true WHERE id = ?")
@Where(clause = "deleted = false")
public class User {

    @Id
    @NotNull
    @GeneratedValue
    private int id;

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
    private String Firstname;

    // @NotNull(message = "O Campo sobrenome não pode ser vazio")
    @Column(name = "lastname")
    @NotEmpty(message = "O campo sobrenome nao pode ser vazio")
    private String Lastname;

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

    // methods

    public User() {
        this.role = ERole.STUDENT;
    }

    public User(String ra,String password) {
    	 this.ra = ra;
        this.password = password;
        this.role = ERole.STUDENT;
    }

    public User(String ra, String password, String firstname, String lastname, String email) {
        this.ra = ra;
        this.password = password;
        this.Firstname = firstname;
        this.Lastname = lastname;
        this.email = email;
        this.role = ERole.STUDENT;
    }

    
}
