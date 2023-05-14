package br.com.Gabriel.dto;

import org.springframework.stereotype.Repository;

import br.com.Gabriel.dto.Mappers.ERole;

import lombok.Data;

@Data
@Repository
public class AuthenticationUserDTO {
    private String ra;
    private String password;
    private ERole role;
	public String getRa() {
		return ra;
	}
	public void setRa(String ra) {
		this.ra = ra;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ERole getRole() {
		return role;
	}
	public void setRole(ERole role) {
		this.role = role;
	}
    
    
}
