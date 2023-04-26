package br.com.Gabriel.dto;

import org.springframework.stereotype.Repository;

import com.br.photos.photosCP.DTO.Mappers.ERole;

import lombok.Data;

@Data
@Repository
public class AuthenticationUserDTO {
    private String username;
    private String password;
    private ERole role;
}
