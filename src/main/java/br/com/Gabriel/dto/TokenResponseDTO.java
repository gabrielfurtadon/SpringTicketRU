package br.com.Gabriel.dto;

import org.springframework.http.ResponseEntity;

import lombok.Data;

@Data
public class TokenResponseDTO {
    private ResponseEntity<String> token;

    public TokenResponseDTO(ResponseEntity<String> token2) {
        this.token = token2;
    }
}
