/*
 * 		COMPRA DE FICHAS
 * 
 * */

package br.com.Gabriel.entities;


import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.Gabriel.dto.Mappers.EStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import br.com.Gabriel.entities.Ficha;

@Entity
@Getter
@Setter
@Table(name = "tb_pedido")
//@SQLDelete(sql = "UPDATE pedidos SET deleted=true WHERE id = ?") // ao invés de deletar faz um update alterando o valor da coluna
//@Where(clause = "deleted = false")
public class Pedido implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Instant momento;
	
	private Double preco;
	
	private int quantidade;
	
	private Integer status;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
	
	public Pedido() {}
	
	public Pedido(int id, Instant momento, int quantidade, double preco, EStatus status, User user) {
		this.id = id;
		this.momento = momento;
		this.quantidade = quantidade;
		this.preco = quantidade * 3.50;
		setStatus(status);
		this.user = user;
		user.setSaldo(user.getSaldo() + quantidade);
	}
	
	public EStatus getStatus() {
		return EStatus.valueOf(status);
	}
	

	public void setStatus(EStatus status) {
		if(status != null) {
		this.status =  status.getCode();
		}
	}

	
	public Double total() {
		Ficha ficha = new Ficha();
		double sum = ficha.preco * quantidade;
		return sum;
	
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return id == other.id;
	}
	
	
	
	
	
	
	
	


}
