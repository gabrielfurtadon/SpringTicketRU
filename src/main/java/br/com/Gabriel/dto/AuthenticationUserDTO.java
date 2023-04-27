package br.com.Gabriel.dto;

import org.springframework.stereotype.Repository;

import br.com.Gabriel.dto.Mappers.ERole;

import lombok.Data;

@Data
@Repository
public class AuthenticationUserDTO {
    private String username;
    private String password;
    private ERole role;
}
