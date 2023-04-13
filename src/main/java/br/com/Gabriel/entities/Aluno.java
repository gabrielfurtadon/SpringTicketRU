package br.com.Gabriel.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_Alunos")
public class Aluno implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private Long ra;
	private String nome;
	private String senha;
	
	public Aluno() {}
	
	


	public Aluno(Long ra, String nome, String senha) {
		super();
		this.ra = ra;
		this.nome = nome;
		this.senha = senha;
	}




	public Long getRa() {
		return ra;
	}




	public void setRa(Long ra) {
		this.ra = ra;
	}




	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}




	@Override
	public int hashCode() {
		return Objects.hash(nome, ra, senha);
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return Objects.equals(nome, other.nome) && Objects.equals(ra, other.ra) && Objects.equals(senha, other.senha);
	}

	
	
	
	
	
}
